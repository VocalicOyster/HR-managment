package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Department;
import it.portfolio.hr.humanResource.entities.Hiring;
import it.portfolio.hr.humanResource.exceptions.department.DepartmentException;
import it.portfolio.hr.humanResource.exceptions.hirirng.*;
import it.portfolio.hr.humanResource.models.DTOs.request.HiringRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.HiringResponseDTO;
import it.portfolio.hr.humanResource.repositories.DepartmentRepository;
import it.portfolio.hr.humanResource.repositories.HiringRepository;
import it.portfolio.hr.humanResource.validator.HiringValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HiringService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HiringRepository hiringRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private HiringValidator hiringValidator;

    public HiringResponseDTO createHiring(HiringRequestDTO hiringRequestDTO, String companyName) throws DepartmentException, HiringException {
        if (hiringValidator.isHiringValid(hiringRequestDTO, companyName)) {
            Hiring hiring = modelMapper.map(hiringRequestDTO, Hiring.class);
            Department department = departmentRepository.findById(hiringRequestDTO.getDepartment_id(), companyName).orElseThrow(() -> new DepartmentException("No department retrieved with id: " + hiring.getDepartment().getId(), 400));
            hiring.setDepartment(department);
            hiring.setCompanyName(companyName);
            hiringRepository.saveAndFlush(hiring);
            return modelMapper.map(hiring, HiringResponseDTO.class);
        }
        throw new HiringException("The inserted hiring's information are not valid", 400);
    }

    public List<HiringResponseDTO> getAll(String companyName) {
        List<Hiring> hiringList = hiringRepository.findAll(companyName);
        List<HiringResponseDTO> hiringResponseDTOList = new ArrayList<>();

        for (Hiring hiring : hiringList) {
            HiringResponseDTO hiringResponseDTO = modelMapper.map(hiring, HiringResponseDTO.class);
            hiringResponseDTOList.add(hiringResponseDTO);
        }

        return hiringResponseDTOList;
    }

    public HiringResponseDTO getById(Long id, String companyName) throws HiringException {
        Hiring hiring = hiringRepository.findById(id, companyName).orElseThrow(() -> new HiringException("No hiring retrieved with id: " + id, 400));
        return modelMapper.map(hiring, HiringResponseDTO.class);
    }

    public HiringResponseDTO updateById(Long id, HiringRequestDTO hiringRequestDTO, String companyName) throws HiringException, DepartmentException {
        Hiring hiring = hiringRepository.findById(id, companyName).orElseThrow(() ->  new HiringException("No hiring retrieved with id: " + id, 400));
        Department department = departmentRepository.findById(hiringRequestDTO.getDepartment_id()).orElseThrow(() -> new DepartmentException("No department retrieved with id: " + hiring.getDepartment().getId(), 400));

        hiring.setDepartment(department);
        hiring.setHiringDate(hiringRequestDTO.getHiringDate());
        hiring.setRole(hiringRequestDTO.getRole());
        hiring.setIBan(hiring.getIBan());
        hiring.setControctsEnum(hiringRequestDTO.getControctsEnum());
        hiring.setPerTimeEnum(hiringRequestDTO.getPerTimeEnum());
        hiringRepository.saveAndFlush(hiring);
        return modelMapper.map(hiring, HiringResponseDTO.class);
    }

    public HiringResponseDTO deleteById(Long id, String companyName) throws HiringException {
        Hiring hiring = hiringRepository.findById(id, companyName).orElseThrow(() -> new HiringException("No hiring retrieved with id: " + id, 400));

        //hiringRepository.deleteById(id);
        hiring.setDeleted(true);
        hiringRepository.saveAndFlush(hiring);
        return modelMapper.map(hiring, HiringResponseDTO.class);
    }
}

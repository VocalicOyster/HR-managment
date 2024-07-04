package it.portfolio.hr.humanResource.seeding;

import it.portfolio.hr.humanResource.entities.*;
import it.portfolio.hr.humanResource.repositories.*;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeedingService {

    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private CandidateEvaluationRepository candidateEvaluationRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private HiringRepository hiringRepository;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private JobAnnouncementRepository jobAnnouncementRepository;
    @Autowired
    private OvertimeRepository overtimeRepository;
    @Autowired
    private PermitsRepository permitsRepository;
    @Autowired
    private SickDaysRepository sickDaysRepository;
    @Autowired
    private VacationsRepository vacationsRepository;

    @Autowired
    private SeedingProvider seedingProvider;

    public boolean initDatabase(String companyName) {
        List<Applicants> applicantsList = applicantRepository.findAll(companyName);
        if(applicantsList.size() > 1) {
            return false;
        }
        seedApplicants();
        seedCandidateEvaluations(companyName);
        seedDepartment();
        seedEmployees();
        seedHiring(companyName);
        seedInterview(companyName);
        seedJobAnnouncement(companyName);
        seedOvertime(companyName);
        seedPermits(companyName);
        seedSickDays(companyName);
        seedVacations(companyName);
        return true;
    }

    public void cleanDatabase(String companyName) {
        deleteApplicants(companyName);
        deleteCandidateEvaluations(companyName);
        deleteDepartment(companyName);
        deleteEmployees(companyName);
        deleteHiring(companyName);
        deleteInterview(companyName);
        deleteJobAnnouncement(companyName);
        deleteOvertime(companyName);
        deletePermits(companyName);
        deleteSickDays(companyName);
        deleteVacations(companyName);
    }

    private void seedApplicants() {
        List<Applicants> applicantsList = seedingProvider.generateApplicant();
        System.out.println("SIIIII" + applicantsList);
//        for(Applicants applicants: applicantsList) {
//            applicantRepository.saveAndFlush(applicants);
//        }
        applicantRepository.saveAll(applicantsList);
    }

    private void seedCandidateEvaluations(String companyName) {
        List<Applicants> applicantsList = applicantRepository.findAll(companyName);
        System.out.println("Candidate applicant " + applicantsList);
        List<CandidateEvaluations> candidateEvaluationsList = seedingProvider.generateCandidateEvaluations(applicantsList);
//        for(CandidateEvaluations candidateEvaluations: candidateEvaluationsList) {
//            candidateEvaluationRepository.saveAndFlush(candidateEvaluations);
//        }
        candidateEvaluationRepository.saveAll(candidateEvaluationsList);

    }

    private void seedDepartment() {
        List<Department> departmentList = seedingProvider.generateDepartment();
        for(Department department: departmentList) {
            departmentRepository.saveAndFlush(department);
        }
    }

    private void seedEmployees() {
        List<Employees> employeesList = seedingProvider.generateEmployees();
        for(Employees employees: employeesList) {
            employeesRepository.saveAndFlush(employees);
        }
    }

    private void seedHiring(String companyName) {
        List<Employees> employeesList = employeesRepository.findAll(companyName);
        List<Department> departmentList = departmentRepository.findAll(companyName);
        List<Hiring> hiringList = seedingProvider.generateHiring(employeesList, departmentList);

        for(Hiring hiring: hiringList) {
            hiringRepository.saveAndFlush(hiring);
        }
    }

    private void seedInterview(String companyName) {
        List<Applicants> applicantsList = applicantRepository.findAll(companyName);
        List<Interview> interviewList = seedingProvider.generateInterview(applicantsList);
        for(Interview interview: interviewList) {
            interviewRepository.saveAndFlush(interview);
        }
    }

    private void seedJobAnnouncement(String companyName) {
        List<Department> departmentList = departmentRepository.findAll(companyName);
        List<JobAnnouncement> jobAnnouncementList = seedingProvider.generateJobAnnouncement(departmentList);
        for(JobAnnouncement jobAnnouncement: jobAnnouncementList) {
            jobAnnouncementRepository.saveAndFlush(jobAnnouncement);
        }
    }

    private void seedOvertime(String companyName) {
        List<Employees> employeesList = employeesRepository.findAll(companyName);
        List<Overtime> overtimeList = seedingProvider.generateOvertime(employeesList);
        for(Overtime overtime: overtimeList) {
            overtimeRepository.saveAndFlush(overtime);
        }
    }

    private void seedPermits(String companyName) {
        List<Employees> employeesList = employeesRepository.findAll(companyName);
        List<Permits> permitsList = seedingProvider.generatePermits(employeesList);
        for(Permits permits: permitsList) {
            permitsRepository.saveAndFlush(permits);
        }
    }

    private void seedSickDays(String companyName) {
        List<Employees> employeesList = employeesRepository.findAll(companyName);
        List<SickDays> sickDaysList = seedingProvider.generateSickDays(employeesList);
        for(SickDays sickDays: sickDaysList) {
            sickDaysRepository.saveAndFlush(sickDays);
        }
    }

    private void seedVacations(String companyName) {
        List<Employees> employeesList = employeesRepository.findAll(companyName);
        List<Vacations> vacationsList = seedingProvider.generateVacations(employeesList);
        for(Vacations vacations: vacationsList) {
            vacationsRepository.saveAndFlush(vacations);
        }
    }

    private void deleteApplicants(String companyName) {
        List<Applicants> applicantsList = applicantRepository.findAll(companyName);
        for(Applicants applicants: applicantsList) {
            applicants.setDeleted(true);
            applicantRepository.saveAndFlush(applicants);
        }
    }

    private void deleteCandidateEvaluations(String companyName) {
        List<CandidateEvaluations> candidateEvaluationsList = candidateEvaluationRepository.findAll(companyName);
        for(CandidateEvaluations candidateEvaluations: candidateEvaluationsList) {
            candidateEvaluations.setDeleted(true);
            candidateEvaluationRepository.saveAndFlush(candidateEvaluations);
        }

    }

    private void deleteDepartment(String companyName) {
        List<Department> departmentList = departmentRepository.findAll(companyName);
        for(Department department: departmentList) {
            department.setDeleted(true);
            departmentRepository.saveAndFlush(department);
        }

    }

    private void deleteEmployees(String companyName) {
        List<Employees> employeesList = employeesRepository.findAll(companyName);
        for(Employees employees: employeesList) {
            employees.setDeleted(true);
            employeesRepository.saveAndFlush(employees);
        }
    }

    private void deleteHiring(String companyName) {
        List<Hiring> hiringList = hiringRepository.findAll(companyName);

        for(Hiring hiring: hiringList) {
            hiring.setDeleted(true);
            hiringRepository.saveAndFlush(hiring);
        }
    }

    private void deleteInterview(String companyName) {
        List<Interview> interviewList = interviewRepository.findAll(companyName);

        for(Interview interview: interviewList) {
            interview.setDeleted(true);
            interviewRepository.saveAndFlush(interview);
        }
    }

    private void deleteJobAnnouncement(String companyName) {
        List<JobAnnouncement> jobAnnouncementList = jobAnnouncementRepository.findAll(companyName);

        for(JobAnnouncement jobAnnouncement: jobAnnouncementList) {
            jobAnnouncement.setDeleted(true);
            jobAnnouncementRepository.saveAndFlush(jobAnnouncement);
        }
    }

    private void deletePermits(String companyName) {
        List<Permits> permitsList = permitsRepository.findAll(companyName);

        for(Permits permits: permitsList) {
            permits.setDeleted(true);
            permitsRepository.saveAndFlush(permits);
        }
    }

    private void deleteOvertime(String companyName) {
        List<Overtime> overtimeList = overtimeRepository.findAll(companyName);

        for(Overtime overtime: overtimeList) {
            overtime.setDeleted(true);
            overtimeRepository.saveAndFlush(overtime);
        }

    }

    private void deleteSickDays(String companyName) {
        List<SickDays> sickDaysList = sickDaysRepository.findAll(companyName);

        for(SickDays sickDays: sickDaysList) {
            sickDays.setDeleted(true);
            sickDaysRepository.saveAndFlush(sickDays);
        }
    }

    private void deleteVacations(String companyName) {
        List<Vacations> vacationsList = vacationsRepository.findAll(companyName);

        for(Vacations vacations: vacationsList) {
            vacations.setDeleted(true);
            vacationsRepository.saveAndFlush(vacations);
        }
    }

}

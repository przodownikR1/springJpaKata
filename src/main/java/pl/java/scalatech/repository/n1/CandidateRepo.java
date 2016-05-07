package pl.java.scalatech.repository.n1;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.example.n1.JobCandidate;

public interface CandidateRepo extends JpaRepository<JobCandidate, Long>{

}

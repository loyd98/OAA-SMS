package com.ateneo.server.repository;

import com.ateneo.server.domain.Donation;
import com.ateneo.server.domain.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    Donor findDonorByAccountNumber(String accountNumber);

    List<Donor> findAllByOrderByIdAsc();
    List<Donor> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM donor d INNER JOIN moa m ON m.donor_account_number = d.account_number WHERE m.foreign_donation_id = ?1", nativeQuery = true)
    List<Donor> findDonorsOfDonation(Long donationId);

    @Query(value = "SELECT * FROM donor WHERE id LIKE %?1% OR donor_name LIKE %?1% OR account_number LIKE %?1% OR account_name LIKE %?1% OR email_address LIKE %?1%", nativeQuery = true)
    List<Donor> search(String keyword);
}

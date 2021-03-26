package com.ateneo.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Donor extends Auditable implements Comparable<Donor>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long donationId;
    private Long scholarshipId;
    private String donorName;

    @NotBlank(message = "Cannot have an empty account number field.")
    private String accountNumber;

    private String accountName;
    private String companyTIN;
    private String companyAddress;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String phone1;
    private String phone2;
    private String faxNumber;
    private String cellphoneNumber;
    private String emailAddress;
    private String salutation;
    private String birthDate;
    private String notes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "donor_donation",
            joinColumns = @JoinColumn(name = "donor_account_number"),
            inverseJoinColumns = @JoinColumn(name = "donation_id")
    )
    private List<Donation> donations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "donor_scholarship",
            joinColumns = @JoinColumn(name = "donor_account_number"),
            inverseJoinColumns = @JoinColumn(name = "scholarship_id")
    )
    private List<Scholarship> scholarships = new ArrayList<>();

    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    @Override
    public int compareTo(Donor anotherDonor) {
        return this.getId().compareTo(anotherDonor.getId());
    }

}

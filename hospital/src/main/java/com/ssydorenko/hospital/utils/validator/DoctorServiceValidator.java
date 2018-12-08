package com.ssydorenko.hospital.utils.validator;


import com.ssydorenko.hospital.db.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DoctorServiceValidator {

    @Autowired
    private DoctorRepository doctorRepository;


    public void validateDoctorIdExists(long doctorId) {

        if (!doctorRepository.existsById(doctorId)) {

            throw new IllegalArgumentException("Doctor with id " + doctorId + " does not exist");
        }
    }


    public void validateDoctorNameNotExists(String doctorFullName) {

        if(doctorRepository.findByFullName(doctorFullName) != null) {

            throw new IllegalArgumentException("Doctor with full name " + doctorFullName + " already exists");
        }
    }

}

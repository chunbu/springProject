package Team2.youngcha.hellospring.service;

import Team2.youngcha.hellospring.domain.Reservation;
import Team2.youngcha.hellospring.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Long join(Reservation reservation) {
        validateDuplicateTable(reservation);
        reservationRepository.save(reservation);
        return reservation.getOid();
    }


    private void validateDuplicateTable(Reservation reservation) {
        reservationRepository.findByResDate(reservation)
                .ifPresent(r -> {throw new IllegalStateException("존재하는게 맞음");});
    }

    public List<Reservation> listsReservation() {
        return reservationRepository.findAll();
    }

    public List<Reservation> cancel(String id) {
        List<Reservation> result = reservationRepository.findByCustomerID(id);
        return result;
    }

    public String customerArrival(String id) {
        return reservationRepository.customerArrival(id);

    }

    public Long tableReallocation(Long oid,int tableNo) {
        return reservationRepository.tableReallocation(oid,tableNo);

    }

    public List<Reservation> listReservationByCustomerId(String id){
        List<Reservation> byCustomerID = reservationRepository.findByCustomerID(id);
        return byCustomerID;
    }

    public void cancel(Long oid){
        reservationRepository.cancelReservation(oid);
    }
}

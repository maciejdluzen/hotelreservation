let ReceptionistUtils = {

    renderReservationTable : function () {
        return `<table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Numer</th>
                        <th>Imię i nazwisko</th>
                        <th>Zameldowanie</th>
                        <th>Wymeldowanie</th>
                        <th>Status</th>
                        <th>Akcje</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="reservationsTableBody">

                    </tbody>
                </table>`;
        },


    getAllFutureReservations : function () {
        $.ajax({
            url : "/auth/receptionist/reservations-future",
            type : 'GET',
            success : function (result, status, xhr) {
                $('#pageTitle').html('Przyszłe rezerwacje');
                $('#reservationsTable').html(ReceptionistUtils.renderReservationTable());
                let reservations = result;
                console.log(reservations);
                console.log(xhr.status);
                if(xhr.status === 200) {
                    let output = '';
                    let resStatus = '';
                    for(let i in reservations) {
                        if(reservations[i].status === true) {
                            resStatus = 'Potwierdzona'
                        } else {
                            resStatus = 'Nie-potwierdzona'
                        };
                    output +=
                         `<tr>
                             <td>${reservations[i].reservationNumber}</td>
                             <td>${reservations[i].guestFirstName} ${reservations[i].guestLastName}</td>
                             <td>${reservations[i].checkInDate}</td>
                             <td>${reservations[i].checkOutDate}</td>
                             <td>${resStatus}</td>
                             <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="">Szczegóły</button></td>
                             <td><button type="button" class="btn btn-outline-dark btn-sm">Odwołaj</button></td>
                         </tr>`;
                    };
                    $('#reservationsTableBody').html(output);
                    }
                }
            });
        }
}
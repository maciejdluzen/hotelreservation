let ReceptionistUtils2 = {

    getAllCurrentReservations : function () {
        console.log("Method being called");
        ReceptionistUtils.showReservationFilters();
        $.ajax({
            url : "/auth/receptionist/reservations-current",
            type : 'GET',
            success : function (result, status, xhr) {
                $('#pageTitle').html('Obecne rezerwacje');
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
                             <td>${reservations[i].roomRoomNumber}</td>
                             <td>${resStatus}</td>
                             <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="ReceptionistUtils.getReservationDetails(${reservations[i].id})">Szczegóły</button></td>
                             <td></td>
                         </tr>`;
                    };
                    $('#reservationsTableBody').html(output);
                }
            }
        });
    },

    getAllPastReservations : function () {
        console.log("Method being called");
        ReceptionistUtils.showReservationFilters();
        $.ajax({
            url : "/auth/receptionist/reservations-past",
            type : 'GET',
            success : function (result, status, xhr) {
                $('#pageTitle').html('Przeszłe rezerwacje');
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
                             <td>${reservations[i].roomRoomNumber}</td>
                             <td>${resStatus}</td>
                             <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="ReceptionistUtils.getReservationDetails(${reservations[i].id})">Szczegóły</button></td>
                             <td></td>
                         </tr>`;
                    };
                    $('#reservationsTableBody').html(output);
                }
            }
        });
    },



}
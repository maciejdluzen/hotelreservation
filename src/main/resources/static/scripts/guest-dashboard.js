let GuestUtils = {

    renderReservationTable : function () {
        return `<table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Numer</th>
                        <th>Hotel</th>
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

    getAllGuestReservations : function () {
        GuestUtils.showReservationFilters();
        $.ajax({
            url : "/auth/guest/reservations",
            type : 'GET',
            success : function (result, status, xhr) {

                $('#pageTitle').html('Moje rezerwacje');
                $('#reservationsTable').html(GuestUtils.renderReservationTable());
                let reservations = result;

                if (xhr.status === 200) {
                    let output = '';
                    let resStatus = '';
                    for(let i in reservations) {
                        if(reservations[i].status === true) {
                            resStatus = 'Potwierdzona'
                        } else {
                            resStatus = 'Nie-potwierdzona'
                        };

                        if($('#checkboxPast').is(':checked') && GuestUtils.pastReservationCheck(reservations[i].checkOutDate)) {
                            output +=
                                `<tr>
                                    <td>${reservations[i].reservationNumber}</td>
                                    <td>${reservations[i].hotelName}</td>
                                    <td>${reservations[i].checkInDate}</td>
                                    <td>${reservations[i].checkOutDate}</td>
                                    <td>${resStatus}</td>
                                    <td><button type="button" class="btn btn-outline-primary btn-sm">Szczegóły</button></td>
                                    <td></td>
                                </tr>`;
                        } else if ($('#checkboxCurrent').is(':checked') && GuestUtils.currentReservationCheck(reservations[i].checkInDate, reservations[i].checkOutDate)) {
                            output +=
                                `<tr>
                                    <td>${reservations[i].reservationNumber}</td>
                                    <td>${reservations[i].hotelName}</td>
                                    <td>${reservations[i].checkInDate}</td>
                                    <td>${reservations[i].checkOutDate}</td>
                                    <td>${resStatus}</td>
                                    <td><button type="button" class="btn btn-outline-primary btn-sm">Szczegóły</button></td>
                                    <td></td>
                                </tr>`;
                        } else if ($('#checkboxFuture').is(':checked') && GuestUtils.futureReservationCheck(reservations[i].checkInDate)) {
                            output +=
                                `<tr>
                                    <td>${reservations[i].reservationNumber}</td>
                                    <td>${reservations[i].hotelName}</td>
                                    <td>${reservations[i].checkInDate}</td>
                                    <td>${reservations[i].checkOutDate}</td>
                                    <td>${resStatus}</td>
                                    <td><button type="button" class="btn btn-outline-primary btn-sm">Szczegóły</button></td>
                                    <td><button type="button" class="btn btn-outline-dark btn-sm">Odwołaj</button></td>
                                </tr>`;
                        }
                    };
                    $('#reservationsTableBody').html(output);
                } else if (xhr.status === 204) {
                    let noContentInfo =
                        `<tr>
                            <td></td>
                            <td>Brak zapisanych rezerwacji</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>`;
                    $('#reservationsTableBody').html(noContentInfo);
                };
            }
        });
    },

    pastReservationCheck : function (checkOut) {
        let today = GuestUtils.todayDate();
        let checkOutDate = GuestUtils.stringToDate(checkOut);
        if(checkOutDate.getTime() < today.getTime()) {
            return true;
        } else {
            return false;
        }
    },

    futureReservationCheck : function (checkIn) {
        let today = GuestUtils.todayDate();
        let checkInDate = GuestUtils.stringToDate(checkIn);
        if(checkInDate.getTime() > today.getTime()) {
            return true;
        } else {
            return false;
        }
    },

    currentReservationCheck : function (checkIn, checkOut) {
        let today = GuestUtils.todayDate();
        let checkInDate = GuestUtils.stringToDate(checkIn);
        let checkOutDate = GuestUtils.stringToDate(checkOut);
        if(checkInDate.getTime() <= today.getTime() && checkOutDate.getTime() >= today.getTime()) {
            return true;
        } else {
            return false;
        }
    },

    todayDate : function() {
        let today = new Date();
        let dd = today.getDate();
        let mm = today.getMonth();
        let yyyy = today.getFullYear();
        return new Date(yyyy, mm, dd);
    },

    stringToDate : function(str) {
        let arr = str.split("-");
        let dd = arr[2] - 0;
        let mm = arr[1] - 1;
        let yyyy = arr[0] - 0;
        return new Date(yyyy, mm, dd);
    },

    showReservationFilters : function () {
        $('#reservationsFilters').show();
    },

    hideReservationFilters : function () {
        $('#reservationsFilters').hide();
    }
}

GuestUtils.hideReservationFilters();
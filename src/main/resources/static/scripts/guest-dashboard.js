console.log('Script attached!');

let GuestUtils = {

    renderReservationFilters : function () {
        return  `<div class="col-md-4 mb-4 bg-secondary text-white">
                     <div class="form-check form-check-inline">
                         <input class="form-check-input" type="checkbox" id="checkbox1" value="option1">
                         <label class="form-check-label font-italic" for="checkbox1">Przeszłe</label>
                     </div>
                     <div class="form-check form-check-inline">
                         <input class="form-check-input" type="checkbox" id="checkbox2" value="option2">
                         <label class="form-check-label font-italic" for="checkbox2">Trwające</label>
                     </div>
                     <div class="form-check form-check-inline">
                         <input class="form-check-input" type="checkbox" id="checkbox3" value="option1">
                         <label class="form-check-label font-italic" for="checkbox3">Przyszłe</label>
                     </div>
                </div>`;
    },

    renderReservationTable : function () {
        return `<table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Numer</th>
                        <th>Hotel</th>
                        <th>Zameldowanie</th>
                        <th>Wymeldowanie</th>
                        <th>Akcje</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="reservationsTableBody">

                    </tbody>
                </table>`;
    },

    getAllGuestReservations : function () {
        $.ajax({
            url : "/auth/guest/reservations",
            type : 'GET',
            success : function (result, status, xhr) {

                $('#pageTitle').html('Moje rezerwacje');
                $('#reservationsFilters').html(GuestUtils.renderReservationFilters());
                $('#reservationsTable').html(GuestUtils.renderReservationTable());

                let reservations = result;
                console.log('Success method: ' + result);

                if (xhr.status === 200) {
                    let output = '';
                    for(let i in reservations) {
                        output +=
                            `<tr>
                                <td>${reservations[i].reservationNumber}</td>
                                <td>${reservations[i].hotelName}</td>
                                <td>${reservations[i].checkInDate}</td>
                                <td>${reservations[i].checkOutDate}</td>
                                <td><button type="button" class="btn btn-outline-primary btn-sm">Szczegóły</button></td>
                                <td><button type="button" class="btn btn-outline-dark btn-sm">Odwołaj</button></td>
                            </tr>`;
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
    }
}
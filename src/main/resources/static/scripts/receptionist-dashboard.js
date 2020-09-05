let ReceptionistUtils = {

    filterReservations : function () {
        $("#filterField").on("keyup", function() {
            let value = $(this).val().toLowerCase();
            $("#reservationsTableBody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    },

    showReservationFilters : function () {
        $('#reservationsFilters').show();
        ReceptionistUtils.filterReservations();
    },

    hideReservationFilters : function () {
        $('#reservationsFilters').hide();
    },

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

    getReservationDetails : function (id) {
        $.ajax({
            url : "/auth/receptionist/reservations/" + id,
            type : 'GET',
            success : function (result, status, xhr) {
                console.log("Sending request to id: " + id);
                console.log("Status: " + xhr.status);
                console.log("Result: " + result);
                let details = JSON.parse(result);
                if(xhr.status === 200) {
                    let vat = details.tax;
                    let vatFormatted = vat*100  + '%';
                    let message = details.message;
                    let guestsNames = '-';
                    if(message === null) {
                        message = "Brak wiadomości";
                    };
                    if(details.secondGuestName !== null) {
                        guestsNames += details.secondGuestName;
                        if(details.thirdGuestName !== null) {
                            guestsNames += ' ,' + details.thirdGuestName;
                            if(details.fourthGuestName !== null) {
                                guestsNames += ' ,' + details.fourthGuestName;
                            };
                        };
                    };
                    $(function () {
                        $('#messages').html(
                            `<div id="dialog" title="Basic dialog">
                               
                                <p>Pozostali goście: ${guestsNames}</p>
                                <p class="font-weight-bold">Koszt netto: ${details.totalNetCost} PLN + ${vatFormatted} VAT</p>
                                <p class="font-weight-bold">Koszt brutto: ${details.totalGrossCost} PLN</p>
                                <p>Wiadomość do recepcji:</p>
                                <p class="font-italic">${message}</p>
                        </div>`
                        );
                        $("#dialog").dialog({
                            title: "Szczegóły rezerwacji",
                            autoOpen: true,
                            modal: true,
                            dialogClass: "no-close",
                            width: 500,
                            buttons: [
                                {
                                    text: "OK",
                                    click: function () {
                                        $(this).dialog('close');
                                    }
                                }
                            ]
                        });
                    });
                };
            }
        });
    },

    getAllFutureReservations : function () {
        ReceptionistUtils.showReservationFilters();
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
                             <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="ReceptionistUtils.getReservationDetails(${reservations[i].id})">Szczegóły</button></td>
                             <td><button type="button" class="btn btn-outline-dark btn-sm">Potwierdź</button></td>
                         </tr>`;
                    };
                    $('#reservationsTableBody').html(output);
                    }
                }
            });
        }
}
ReceptionistUtils.hideReservationFilters();
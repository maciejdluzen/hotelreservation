$("#showFormBtn").click(function(){
        $("#newReceptionistForm").toggle();
    });

$('#addHotelBtn').click(function(event) {
    if($('#recepPass').val() !== $('#passConf').val()) {
        event.preventDefault();
        $('#passConfErr').text('Powtórzone hasło musi być takie samo');
    }
});

let ReceptionistAdminUtils = {

    getAllReceptionists : function () {
        $.ajax({
           url : '/auth/admin/receptionists/getall',
           type : 'GET',
           success : function (result, status, xhr) {
                let receptionists = result;
                if(xhr.status === 200) {
                    let output = '';
                    for(let i in receptionists) {
                        if(receptionists[i].active === true) {
                            active = 'Aktywny'
                        } else {
                            active = 'Nieaktywny';
                        }
                        output +=
                            `<tr>
                            <td>${receptionists[i].id}</td>
                            <td>${receptionists[i].lastName}</td>
                            <td>${receptionists[i].firstName}</td>
                            <td>${receptionists[i].emailAddress}</td>
                            <td>${receptionists[i].hotelName}</td>
                            <td>${active}</td>
                            <td><button onclick="ReceptionistAdminUtils.deleteReceptionist(${receptionists[i].id})" class="btn btn-danger btn-sm"><i class="far fa-trash-alt"></i></button></td>
                            <td><button onclick="ReceptionistAdminUtils.deactivateReceptionist(${receptionists[i].id})" class="btn btn-warning btn-sm"><i class="fas fa-user-alt"></i></button></td>
                         </tr>`;
                    }
                    $('#receptionistsTableBody').html(output);
                } else if(xhr.status === 204) {
                    let noContentInfo = `<tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td>Brak zarejestrowanych recepcjonistów</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                         </tr>`
                    $('#receptionistsTableBody').html(noContentInfo);
                }
           },
            error : function() {


            }
        });
    },

    deactivateReceptionist : function (id) {
        if(confirm("Potiwerdź zmianę statusu recepcjonisty:")) {
            $.ajax({
                url : '/auth/admin/receptionists/' + id + '/deactivate',
                type :'PUT',
                success : function (result, status) {
                    setTimeout(function() {
                        location.reload();
                    }, 2000);
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-success'
                    messageField.firstElementChild.innerHTML += '<p>Pomyślnie zmieniono status dla konta</p>'
                },
                error : function(result, status) {
                    setTimeout(function() {
                        window.history.go(0);
                    }, 5000);
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-danger'
                    messageField.firstElementChild.innerHTML += '<p>Błąd przetwarzania</p>'
                }
            });
        }
    }
}
ReceptionistAdminUtils.getAllReceptionists();
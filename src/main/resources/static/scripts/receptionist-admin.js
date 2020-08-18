$("#showFormBtn").click(function(){
        $("#newReceptionistForm").toggle();
    });

if($('#recepPass').val() !== $('#passConf').val()) {
    $('#passConfErr').text() = 'Hasła muszą być identyczne';
}

let ReceptionistAdminUtils = {

    getAllReceptionists : function () {
        $.ajax({
           url : '/auth/admin/receptionists/getall',
           type : 'GET',
           success : function (result, status, xhr) {
                let receptionists = result;
                console.log(receptionists);
                console.log(status);
                console.log($('#receptionistsTableBody'));
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
                            <td>${receptionists[i].active}</td>
                            <td><button onclick="ReceptionistAdminUtils.deleteReceptionist(${receptionists[i].id})" class="btn btn-danger btn-sm"><i class="far fa-trash-alt"></i></button></td>
                            <td><button onclick="ReceptionistAdminUtils.deactivateReceptionist(${receptionists[i].id})" class="btn btn-warning btn-sm"><i class="fas fa-user-alt"></i></button></td>
                         </tr>`;
                    }
                    $('#receptionistsTableBody').html(output);
                    console.log(output);
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
    }
}
ReceptionistAdminUtils.getAllReceptionists();
let RoomTypesUtils = {

    cancelEditRoomType : function () {
        $('#editRoomTypeForm').hide();
        $('#newRoomTypeForm').show();
    },

    prepareForEditRoomType : function (id) {
        this.showEditRoomTypeForm();
        $.ajax({
            url: '/auth/admin/roomtypes/' + id,
            type: 'GET',
            success : function (result) {
               let rmType = JSON.parse(result);
               console.log("Inside prepare function");
               console.log(rmType);
               $('#nameEdit').val(rmType.name);
               $('#noPersonsEdit').val(rmType.noPersons);
               $('#rateNetEdit').val(rmType.rateNet);
               $('#taxEdit').val(rmType.tax);
               $('#descriptionEdit').val(rmType.description);
               $('#feature1Edit').val(rmType.feature1);
               $('#feature2Edit').val(rmType.feature2);
               $('#feature3Edit').val(rmType.feature3);
               $('#feature4Edit').val(rmType.feature4);
               $('#imageNameEdit').val(rmType.imageId);
               $('#editRoomSubmitBtn').val(rmType.id);
            }
        });
    },

    editRoomType : function () {

            rmType2 = {};

            rmType2.id = $('#editRoomSubmitBtn').val();
            rmType2.name = $('#nameEdit').val();
            rmType2.noPersons = $('#noPersonsEdit').val();
            rmType2.rateNet = $('#rateNetEdit').val();
            rmType2.tax = $('#taxEdit').val();
            rmType2.description = $('#descriptionEdit').val();
            rmType2.feature1 = $('#feature1Edit').val();
            rmType2.feature2 = $('#feature2Edit').val();
            rmType2.feature3 = $('#feature3Edit').val();
            rmType2.feature4 = $('#feature4Edit').val();
            rmType2.imageId = $('#imageNameEdit').val();

            console.log(rmType2);

            if(confirm("Edytować pokój?")) {
                $.ajax({
                    url: '/auth/admin/roomtypes/' + rmType2.id,
                    type : "PUT",
                    contentType : "application/json",
                    headers : {
                        "Content-Type" : "application/json",
                        "Accept" : "application/json"
                    },
                    data : JSON.stringify(rmType2),
                    success : function(result, status, xhr) {

                        console.log("Success" + xhr.status);

                        if(xhr.status === 200) {
                            setTimeout(function() {
                                location.reload();
                            }, 2000);
                            let messageField = document.getElementById('messages');
                            messageField.firstElementChild.classList.add('alert');
                            messageField.firstElementChild.classList += ' alert-success'
                            messageField.firstElementChild.innerHTML += '<p>Operacja zakończyła się sukcesem</p>'

                        } else if (xhr.status === 304) {
                            setTimeout(function() {
                                location.reload();
                            }, 2000);
                            let messageField = document.getElementById('messages');
                            messageField.firstElementChild.classList.add('alert');
                            messageField.firstElementChild.classList += ' alert-warning'
                            messageField.firstElementChild.innerHTML += '<p>Popraw błędy w formularzu</p>'
                        }
                    },
                    error : function(result, status, xhr) {


                            setTimeout(function() {
                                location.reload();
                            }, 5000);
                            this.showEditRoomTypeForm();
                            let messageField = document.getElementById('messages');
                            messageField.firstElementChild.classList.add('alert');
                            messageField.firstElementChild.classList += ' alert-danger'
                            messageField.firstElementChild.innerHTML += '<p>Błąd przetwarzania</p>'
                    }
                });
            }
    },

    hideEditRoomTypeForm : function() {
        $('#editRoomTypeForm').hide();
    },

    showEditRoomTypeForm : function () {
        $('#newRoomTypeForm').hide();
        $('#editRoomTypeForm').show();
    },

    getAllRoomsImages : function(id, elementId) {
        const url2 = '/auth/admin/images/' + id;

        fetch(url2)
            .then(function(response) {
                return response.blob();
            })
            .then(function(responseAsBlob) {
                document.getElementById(elementId).src = URL.createObjectURL(responseAsBlob);
            })
            .catch(function() {
                let messageField = document.getElementById('messages');
                messageField.firstElementChild.classList.add('alert');
                messageField.firstElementChild.classList += ' alert-danger'
                messageField.firstElementChild.innerHTML += '<p>Wystąpił błąd podczas pobierania zawartości strony</p>'
        });
    },

    getAllRoomTypesWithoutImage : function() {
        const url = '/auth/admin/roomtypes/getall';

        let roomName = '#roomName';
        let description = '#description';
        let feature1 = '#feature1';
        let feature2 = '#feature2';
        let feature3 = '#feature3';
        let feature4 = '#feature4';
        let noGuests = '#noGuests';
        let rateNet = '#rateNet';
        let tax = '#tax';
        let rateGross = '#rateGross';
        let imageId = '#imageId'
        let buttonsDiv = '#buttonsDiv';

        fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(responseAsJson) {
            for(let i in responseAsJson) {
                if(i === 3) {break; }
                $(roomName + i).html(responseAsJson[i].name)
                $(description + i).html(responseAsJson[i].description)
                $(feature1 + i).html(responseAsJson[i].feature1)
                $(feature2 + i).html(responseAsJson[i].feature2)
                $(feature3 + i).html(responseAsJson[i].feature3)
                $(feature4 + i).html(responseAsJson[i].feature4)
                $(noGuests + i).html('Liczba gości: ' + responseAsJson[i].noPersons)
                $(rateNet + i).html('Cena netto: ' + responseAsJson[i].rateNet + ' PLN')
                let vatFormatted = (responseAsJson[i].tax)*100 + '%';
                $(tax + i).html('VAT: ' + vatFormatted)
                $(rateGross + i).html('Cena brutto: ' + responseAsJson[i].rateGross + ' PLN')
                let imgId = responseAsJson[i].imageId; // image id from the response
                let elId = 'image' + i; // search for the element id to be passed to the function
                let rmId = responseAsJson[i].id;
                console.log("Room id: " + rmId);
                console.log($(buttonsDiv + i));
                $(buttonsDiv + i).append(
                    `<button onclick="RoomTypesUtils.prepareForEditRoomType(${rmId})" type="button" class="btn btn-outline-warning btn-sm mr-3">Edytuj</button>`
                ).append(
                    `<button onclick="RoomTypesUtils.deleteRoomTypeById(${rmId})" class="btn btn-outline-danger btn-sm">Usuń</button>`
                );
                RoomTypesUtils.getAllRoomsImages(imgId, elId);

            }
        })
        .catch(function() {
            let messageField = document.getElementById('messages');
            messageField.firstElementChild.classList.add('alert');
            messageField.firstElementChild.classList += ' alert-danger'
            messageField.firstElementChild.innerHTML += '<p>Wystąpił błąd podczas pobierania zawartości strony</p>'
        });
    },

    deleteRoomTypeById : function(id) {
        if(confirm("Potwierdź usunięcie rodzaju pokoju o id: " + id)) {
            const url3 = '/auth/admin/roomtypes/' + id;

            $.ajax({
                url : url3,
                type : 'DELETE',
                success : function (result, status, xhr) {
                    if(xhr.status === 204) {
                        setTimeout(function () {
                            window.history.go(0);
                        }, 2000);
                        let messageField = document.getElementById('messages');
                        messageField.firstElementChild.classList.add('alert');
                        messageField.firstElementChild.classList += ' alert-success'
                        messageField.firstElementChild.innerHTML += '<p>Usunięto konto recepcjonisty</p>'
                    }
                },
                error : function () {
                        setTimeout(function () {
                            window.history.go(0);
                        }, 2000);
                        let messageField = document.getElementById('messages');
                        messageField.firstElementChild.classList.add('alert');
                        messageField.firstElementChild.classList += ' alert-danger'
                        messageField.firstElementChild.innerHTML += '<p>Operacja usunięcia nie powiodła się</p>'
                }
            });
        }
    }
};

RoomTypesUtils.getAllRoomTypesWithoutImage();
RoomTypesUtils.hideEditRoomTypeForm();



// deleteRoomTypeById : function(id) {
//     if(confirm("Potwierdź usunięcie rodzaju pokoju o id: " + id)) {
//         const url3 = '/auth/admin/roomtypes/' + id;
//
//         fetch(url3, {
//
//             method: 'DELETE'
//         .then(function(response) {
//             return response.json();
//         }).then(function(responseAsJson) {
//             console.log(responseAsJson);
//             console.log("before if-statement");
//             if(response.status === 204) {
//                 // setTimeout(function() {
//                 //     window.history.go(0);
//                 // }, 2000);
//                 let messageField = document.getElementById('messages');
//                 messageField.firstElementChild.classList.add('alert');
//                 messageField.firstElementChild.classList += ' alert-success'
//                 messageField.firstElementChild.innerHTML += '<p>Usunięto rodzaj pokoju</p>'
//             } else if(response.status === 400) {
//                 let responseAsJson = response.json();
//                 console.log("status code 400")
//                 console.log(responseAsJson.message);
//             }
//         }).catch(() => {
//             console.log("In catch block")
//             setTimeout(function() {
//                 window.history.go(0);
//             }, 2000);
//             let messageField = document.getElementById('messages');
//             messageField.firstElementChild.classList.add('alert');
//             messageField.firstElementChild.classList += ' alert-danger'
//             messageField.firstElementChild.innerHTML += '<p>Błąd przetwarzania</p>'
//
//         });
//     }
// },
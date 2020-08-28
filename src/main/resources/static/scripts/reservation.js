let ReservationUtils = {

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
        let imageId = '#imageId';
        let buttonsDiv = '#buttonsDiv';

        fetch(url).then(function (response) {
                return response.json();

            }).then(function (responseAsJson) {

                for(let i in responseAsJson) {

                    if (i === 3) {
                        break;
                    }

                    let vatFormatted = (responseAsJson[i].tax) * 100 + '%';
                    let imgId = responseAsJson[i].imageId; // image id from the response
                    let elId = 'image' + i; // search for the element id to be passed to the function
                    let rmId = responseAsJson[i].id;
                    let roomType = responseAsJson[i].name;

                    $(roomName + i).html(responseAsJson[i].name);
                    $(description + i).html(responseAsJson[i].description);
                    $(feature1 + i).html(responseAsJson[i].feature1);
                    $(feature2 + i).html(responseAsJson[i].feature2);
                    $(feature3 + i).html(responseAsJson[i].feature3);
                    $(feature4 + i).html(responseAsJson[i].feature4);
                    $(noGuests + i).html('Liczba gości: ' + responseAsJson[i].noPersons);
                    $(rateNet + i).html('Cena netto: ' + responseAsJson[i].rateNet + ' PLN');
                    $(tax + i).html('+ ' + vatFormatted + ' VAT');
                    $(rateGross + i).html('Cena brutto: ' + responseAsJson[i].rateGross + ' PLN');
                    $(buttonsDiv + i).append(
                        `<a class='btn btn-outline-secondary' href='/auth/guest/reservation/details?roomType=${roomType}'>Wybierz</a>`
                    );
                    console.log(roomType);


                    //`<button onclick="ReservationUtils.redirectToReservationDetailsPage(${roomType})" type="button" class="btn btn-outline-secondary">Wybierz</button>`

                    ReservationUtils.getAllRoomsImages(imgId, elId);
                }
            }).catch(function () {
                let messageField = document.getElementById('messages');
                messageField.firstElementChild.classList.add('alert');
                messageField.firstElementChild.classList += ' alert-danger';
                messageField.firstElementChild.innerHTML += '<p>Wystąpił błąd podczas pobierania zawartości strony</p>';
            });
    },

    redirectToReservationDetailsPage : function(rmType) {
        $.ajax({
            url : '/auth//guest/reservation/details?roomType=' + rmType,
            type : 'GET'
        });
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
                messageField.firstElementChild.classList += ' alert-danger';
                messageField.firstElementChild.innerHTML += '<p>Wystąpił błąd podczas pobierania zawartości strony</p>';
            });
    }
};

ReservationUtils.getAllRoomTypesWithoutImage();
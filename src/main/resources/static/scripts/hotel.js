var HotelUtils = {

    prepareForEditHotel : function(hotelId) {
        this.showEditUtils();
        $.ajax({
            url : '/auth/admin/hotels/' + hotelId,
            type : 'GET',
            success : function(result, status) {
               let hotel = JSON.parse(result);

                document.getElementById('editHotelBtn').value = hotel.id;
                document.getElementById('hotelName2').value = hotel.name;
                document.getElementById('hotelStreet2').value = hotel.street;
                document.getElementById('hotelNumber2').value = hotel.number;
                document.getElementById('hotelCity2').value = hotel.city;
                document.getElementById('hotelPostCode2').value = hotel.postCode;
                document.getElementById('hotelPhoneNumber2').value = hotel.phoneNumber;
                document.getElementById('hotelEmailAddress2').value = hotel.emailAddress;

            }
        });
    },

    editHotel : function() {
                hotel2 = {};

                hotel2.id = document.getElementById('editHotelBtn').value;
                hotel2.name = document.getElementById('hotelName2').value;
                hotel2.street = document.getElementById('hotelStreet2').value;
                hotel2.number = document.getElementById('hotelNumber2').value;
                hotel2.city = document.getElementById('hotelCity2').value;
                hotel2.postCode = document.getElementById('hotelPostCode2').value;
                hotel2.phoneNumber = document.getElementById('hotelPhoneNumber2').value;
                hotel2.emailAddress = document.getElementById('hotelEmailAddress2').value;

                if(confirm("Edytować hotel?")) {
                    $.ajax({
                        url : '/auth/admin/hotels/' + hotel2.id,
                        type : "PUT",
                        contentType : "application/json",
                        headers : {
                            "Content-Type" : "application/json",
                            "Accept" : "application/json"
                        },
                        data : JSON.stringify(hotel2),
                        success : function(result, status) {
                            setTimeout(function() {
                                location.reload();
                            }, 2000);
                            let messageField = document.getElementById('messages');
                            messageField.firstElementChild.classList.add('alert');
                            messageField.firstElementChild.classList += ' alert-success'
                            messageField.firstElementChild.innerHTML += '<p>Pomyślnie zaktualizowano hotel</p>'
                            console.log("Edited hotel!");
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
    },

    hideEditForm : function() {
        $('#editHotelForm').hide();
    },

    showEditUtils : function() {
        $('#editHotelForm').show();
    },

    /* delete hotel*/
    deleteHotel: function(id) {
        if(confirm("Potwierdź usunięcie hotelu o id " + id)) {
            $.ajax({
                url : '/auth/admin/hotels/' + id,
                type : 'DELETE',
                success : function (result, status) {
                    setTimeout(function() {
                        window.history.go(0);
                    }, 2000);
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-success'
                    messageField.firstElementChild.innerHTML += '<p>Usunięto hotel</p>'
                },
                error : function(result, status) {

                    setTimeout(function() {
                        window.history.go(0);
                    }, 2000);
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-danger'
                    messageField.firstElementChild.innerHTML += '<p>Błąd przetwarzania</p>'

                }
            });
        }
    }
    /* delete hotel - END */


};

HotelUtils.hideEditForm();
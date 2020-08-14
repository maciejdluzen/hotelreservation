var HotelUtils = {

    prepareForEditHotel : function(hotelId) {
        this.showEditUtils();
        $.ajax({
            url : '/auth/admin/hotels/' + hotelId,
            type : 'GET',
            success : function(result, status) {
                var hotel = JSON.parse(result);

                document.getElementById('editHotelBtn').value = hotel.id;
                document.getElementById('hotelName').value = hotel.name;
                document.getElementById('hotelStreet').value = hotel.street;
                document.getElementById('hotelNumber').value = hotel.number;
                document.getElementById('hotelCity').value = hotel.city;
                document.getElementById('hotelPostCode').value = hotel.postCode;
                document.getElementById('hotelPhoneNumber').value = hotel.phoneNumber;
                document.getElementById('hotelEmailAddress').value = hotel.emailAddress;
            }
        });
    },

    hideEditButtons : function() {
        $('#addHotelBtn').show();
        $('#editHotelBtn').hide();
    },

    showEditUtils : function() {
        $('#addHotelBtn').hide();
        $('#editHotelBtn').show();
        $('#newHotelForm').show();
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

HotelUtils.hideEditButtons();
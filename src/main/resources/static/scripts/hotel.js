var HotelUtils = {

    deleteHotel: function (id) {
        if(confirm("Please confirm, that you wish to delete the hotel with id " + id)) {
            $.ajax({
                url : '/auth/admin/hotels/' + id,
                type : 'DELETE',
                success : function (result, status) {
                    setTimeout(function() {
                        window.location.href = "http://localhost:8081/auth/admin/hotels"
                    }, 3000);
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-success'
                    messageField.firstElementChild.innerHTML += '<p>Usunięto hotel</p>'
                },
                error : function(result, status) {

                }
            });
        }
    }
};
var HotelUtils = {

    deleteHotel: function (id) {
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
};
var RoomUtils = {

    deleteRoom : function(roomId) {
        if(confirm("Potwierdź usunięcie pokoju o id "+ roomId)) {
            $.ajax({
                url : '/auth/admin/hotels/rooms/' + roomId,
                type : 'DELETE',
                success : function (result, status) {
                    setTimeout(function() {
                        window.history.go(0);
                    }, 2000);
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-success'
                    messageField.firstElementChild.innerHTML += '<p>Usunięto pokój</p>'
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
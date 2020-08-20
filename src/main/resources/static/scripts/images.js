let ImagesUtils = {

    toggleImage : function (id) {
        let imgId = '#roomImage' + id;
        this.hideAllImages();
        $(imgId).toggle();
    },

    hideAllImages : function () {
     $('img').hide();
    },

    deleteImage : function(id) {
        if(confirm("Potwierdź usunięcie zdjęcia o id " + id)) {
            $.ajax({
                url : '/auth/admin/images/' + id,
                type : 'DELETE',
                success : function (result, status) {
                    setTimeout(function() {
                        window.history.go(0);
                    }, 2000);
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-success'
                    messageField.firstElementChild.innerHTML += '<p>Usunięto zdjęcie</p>'
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
}
ImagesUtils.hideAllImages();
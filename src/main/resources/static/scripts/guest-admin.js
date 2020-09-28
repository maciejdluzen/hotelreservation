let GuestAdminUtils = {

    gelAllGuestsWithoutDetails : function() {

        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/auth/admin/guests/getall', true);

        xhr.onload = function() {
            if(this.status === 200) {
                let guests = JSON.parse(this.responseText);
                console.log(guests);
                let output = '';
                let active = '';
                for(let i in guests) {
                    if(guests[i].active === true) {
                        active = 'Aktywny'
                    } else {
                        active = 'Nieaktywny';
                    }
                    output +=
                        `<tr>
                            <td>${guests[i].id}</td>
                            <td>${guests[i].lastName}</td>
                            <td>${guests[i].firstName}</td>
                            <td>${guests[i].emailAddress}</td>
                            <td>${active}</td>
                            <td><button onclick="GuestAdminUtils.deleteGuest(${guests[i].id})" class="btn btn-danger btn-sm"><i class="far fa-trash-alt"></i></button></td>
                            <td><button onclick="GuestAdminUtils.deactivateGuest(${guests[i].id})" class="btn btn-warning btn-sm"><i class="fas fa-user-alt"></i></button></td>
                         </tr>`;
                }
                document.getElementById('guestTableBody').innerHTML = output
            } else if(this.status === 204) {
                output =
                    `<tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Brak zarejestrowanych użytkowników</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>`;
                document.getElementById('guestTableBody').innerHTML = output
            }
        }
        xhr.send();
    },

    deleteGuest : function(id) {
        let xhr2 = new XMLHttpRequest();
        let url = '/auth/admin/guests/' + id;
        let method = 'DELETE';
            xhr2.open(method, url, true);
            xhr2.onload = function() {
                if(xhr2.status === 200) {
                    location.reload();

                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-success'
                    messageField.firstElementChild.innerHTML += '<p>Usunięto użytkownika</p>'

                } else if (xhr2.status === 404) {
                    location.reload();
                    let messageField = document.getElementById('messages');
                    messageField.firstElementChild.classList.add('alert');
                    messageField.firstElementChild.classList += ' alert-danger'
                    messageField.firstElementChild.innerHTML += '<p>Błąd przetwarzania</p>'
                }
            }
        if(confirm("Czy na pewno chcesz usunąć użytkownika?")) {
            xhr2.send();
        }
    },

    deactivateGuest : function(id) {

        if(confirm('Potwierdź zmianę statusu gościa')) {
            $.ajax({
                url : '/auth/admin/guests/' + id + '/deactivate',
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
GuestAdminUtils.gelAllGuestsWithoutDetails();


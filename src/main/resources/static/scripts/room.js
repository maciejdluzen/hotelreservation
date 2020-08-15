var RoomUtils = {

    prepareForEditRoom : function(roomId) {
        console.log("Inside prepare for EditRoom function!");
        this.showEditForm();
        $.ajax({
           url : '/auth/admin/hotels/rooms/' + roomId,
           type : 'GET',
           success : function (result, status) {
               console.log(result);
               let room = JSON.parse(result);

               document.getElementById('editRoomBtn').value = room.id;
               document.getElementById('roomNumber2').value = room.roomNumber;
               document.getElementById('floorNumber2').value = room.floorNumber;
               document.getElementById('roomTypeName2').value = room.roomTypeName;
               document.getElementById('hotelId2').value = room.hotelId;
               console.log(room);
           }
        });
    },

    editRoom : function() {
      room2 = {};

      room2.id = document.getElementById('editRoomBtn').value;
      room2.roomNumber = document.getElementById('roomNumber2').value;
      room2.floorNumber = document.getElementById('floorNumber2').value;
      room2.roomTypeName = document.getElementById('roomTypeName2').value;
      room2.hotelId = document.getElementById('hotelId2').value;

      if(confirm("Edytować pokój")) {
       $.ajax({
           url : '/auth/admin/hotels/rooms/' + room2.id,
           type : 'PUT',
           contentType : "application/json",
           headers : {
               "Content-Type" : "application/json",
               "Accept" : "application/json"
           },
           data : JSON.stringify(room2),
           success : function(result, status) {
               setTimeout(function() {
                   //location.reload();
               }, 2000);
               let messageField = document.getElementById('messages');
               messageField.firstElementChild.classList.add('alert');
               messageField.firstElementChild.classList += ' alert-success'
               messageField.firstElementChild.innerHTML += '<p>Pomyślnie zaktualizowano pokój</p>'
               console.log("Edited room!");
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

    showEditForm : function() {
      $('#editRoomForm').show();
    },

    hideEditForm : function() {
        $('#editRoomForm').hide();
    },

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

RoomUtils.hideEditForm();
let checkIn = document.getElementById('checkin').value;
let checkOut = document.getElementById('checkout').value;
let submitBtn = $('#reservationSubmit');

submitBtn.click(function(event) {
    let checkIn = stringToDate(document.getElementById('checkin').value);
    let checkOut = stringToDate(document.getElementById('checkout').value);
    let today = todayDate();

    if(!checkDates(checkIn, checkOut, today)) {
        event.preventDefault();
        if($('#checkInGroup').children().length === 2) {
            $('#checkInGroup').append(
                `<small class="form-text form-muted text-danger">Wybierz poprawne daty przyjazdu/wyjazdu</small>`
            );
        }
        console.log("Wrong dates")
    } else {
        console.log("OK")
    }
});

function todayDate() {
    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth();
    let yyyy = today.getFullYear();
    return new Date(yyyy, mm, dd);
};

function stringToDate(str) {
    let arr = str.split("-");
    let dd = arr[2] - 0;
    let mm = arr[1] - 1;
    let yyyy = arr[0] - 0;
    return new Date(yyyy, mm, dd);
};

function checkDates(checkIn, checkOut, today) {
    if(checkIn.getTime() <= today.getTime() || checkOut.getTime() <= today.getTime() || checkOut.getTime() < checkIn.getTime()) { // || isNaN(checkIn.getTime) || isNaN(checkOut.getTime)
        return false;
    } else if(checkIn.getTime() === checkOut.getTime()) {
        return false;
    } else if(isNaN(checkIn.getTime()) || isNaN(checkOut.getTime())) {
        return false;
    } else {
        return true;
    }
};
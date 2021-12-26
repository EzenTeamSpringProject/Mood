const dropDown = document.getElementById('dropDown');
const dropDownBtn = document.querySelector('#profile button');

function handleDropDown() {
    if (dropDown.classList.value === 'hidden') {
        dropDown.classList.remove('hidden');
    } else {
        dropDown.classList.add('hidden');
    }
}

dropDownBtn.addEventListener('click', handleDropDown);
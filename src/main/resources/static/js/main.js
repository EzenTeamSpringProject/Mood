// profile button
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

// mypage
const editBtn = document.querySelector('.editBtn');
const infoForm = document.querySelector('.info form');
const name = document.querySelector('.info h1');
const des = document.querySelector('.info p');

function handleEditBtn() {
  infoForm.classList.remove('hidden');
  name.classList.add('hidden');
  des.classList.add('hidden');
  editBtn.classList.add('hidden');
}

editBtn.addEventListener('click', handleEditBtn);

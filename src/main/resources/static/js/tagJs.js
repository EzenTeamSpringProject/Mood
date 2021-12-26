const tagIsEmpty = tag => {
    if (tag == undefined || tag == null || tag.length <= 0) {
        alert("태그가 비어있습니다.");
        return false;
    }
    return true;
};

const makeTag = tag => {
    const tagWrap = document.createElement("div");
    tagWrap.className = "tag";
    tagWrap.addEventListener("click", removeTag);

    const tagTitle = document.createElement("p");
    tagTitle.className = "tagTitle";
    tagTitle.textContent = tag;

    tagWrap.appendChild(tagTitle);

    return tagWrap
};

const appendTag = (parentId, referenceId, newNode) => {
    const parentElement = document.querySelector(`#${parentId}`);
    const referenceElement = document.querySelector(`#${referenceId}`);
    parentElement.insertBefore(newNode,referenceElement );
};

const clearTextContent = targetId => {
    const target = document.querySelector(`#${targetId}`);
    target.value = '';
};

const addTag = e => {
    const {keyCode, target} = e;
    const {value: tagText} = target;

    if (keyCode == 13 && tagIsEmpty(tagText)) {
        appendTag("tag-list", "tag", makeTag(tagText));
        clearTextContent("tag");
    }
};

const removeTag = e => {
    e.currentTarget.remove();
};

const tagInput = document.querySelector("#tag");
tagInput.addEventListener("keyup", addTag);



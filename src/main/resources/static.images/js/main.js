
const menuBtn = document.getElementById("menu-btn");
const navLiens = document.getElementById("nav-lien");
const menuBtnIcon = document.querySelector("i");

menuBtn.addEventListener("click", () => {
    navLiens.classList.toggle("open");
    const isOpen = navLiens.classList.contains("open");
    menuBtnIcon.setAttribute("class", isOpen ? "ri-close-line" : "ri-menu-line");
});

navLiens.addEventListener("click", () => {
    navLiens.classList.remove("open");
    menuBtnIcon.setAttribute("class", "ri-menu-line");


});


const navSearch = document.getElementById("nav-search");

navSearch.addEventListener("click", () => {
    navSearch.classList.toggle("open");
});


const scrollOption = {
    distance: "50px",
    origin: "bottom",
    duration: 1000
};

ScrollReveal().reveal(".header_image img", {
    ...scrollOption,
    origin: "right",
    duration: 1000,
    delay: 500

});

ScrollReveal().reveal(".header_content div", {
    duration: 1000,
    delay: 500
});


ScrollReveal().reveal(".header_content img", {
    ...scrollOption,
    delay: 1000
});

ScrollReveal().reveal(".header_content p", {
    ...scrollOption,
    delay: 1000
});




ScrollReveal().reveal(".produit_affichage", {
    ...scrollOption,
    interval: 500
});


ScrollReveal().reveal(".about_image img", {
    ...scrollOption,
    origin: "right"
});

ScrollReveal().reveal(".about_gp", {
    duration: 1000,
    interval: 500,
    delay: 500
});


const swiper = new Swiper(".swiper", {
    loop: true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false
    }
});


window.addEventListener('load', () => {
    document.body.classList.add('page-loaded');
});



const productCards = document.querySelectorAll('.product_card');
const popup = document.getElementById('product_popup');
const popupImage = document.getElementById('popup_image');
const popupTitle = document.getElementById('popup_title');
const popupDescription = document.getElementById('popup_description');
const closePopup = document.querySelector('.close_popup');

productCards.forEach((card) => {
    card.addEventListener('click', () => {
        const image = card.querySelector('img').src;
        const title = card.querySelector('h3').textContent;
        const description = card.querySelector('p').textContent;

        popupImage.src = image;
        popupTitle.textContent = title;
        popupDescription.textContent = description;
        popup.style.display = 'flex';
    });
});

closePopup.addEventListener('click', () => {
    popup.style.display = 'none';
});

popup.addEventListener('click', (e) => {
    if (e.target === popup) {
        popup.style.display = 'none';
    }
});


const sr = ScrollReveal({
    origin: 'bottom',
    distance: '60px',
    duration: 1000,
    delay: 200,
    reset: true
});

sr.reveal('.produit_container', {delay: 300});
sr.reveal('.client_container', {delay: 500});
sr.reveal('.yap_list', {delay: 600});
sr.reveal('.section_container', {delay: 600});

sr.reveal('.produit_grid', {
    interval: 200,
    origin: 'bottom',
    distance: '50px'
});



sr.reveal('.swiper-slide', {
    interval: 300,
    origin: 'bottom',
    distance: '50px'
});


document.addEventListener('DOMContentLoaded', function () {
    const slider = document.querySelector('.listt');
    const items = document.querySelectorAll('.list_item');

    const clones = Array.from(items).map(item => {
        const clone = item.cloneNode(true);
        clone.setAttribute('aria-hidden', 'true');
        return clone;
    });
    slider.append(...clones);

    let animationId;
    const speed = 1;
    let position = 0;
    const totalWidth = slider.scrollWidth / 2;

    const animate = () => {
        position -= speed;
        if (position < -totalWidth)
            position = 0;
        slider.style.transform = `translateX(${position}px)`;
        animationId = requestAnimationFrame(animate);
    };

    animationId = requestAnimationFrame(animate);

    window.addEventListener('beforeunload', () => {
        cancelAnimationFrame(animationId);
    });
});
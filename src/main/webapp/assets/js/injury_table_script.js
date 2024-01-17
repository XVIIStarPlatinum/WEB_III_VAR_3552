function handlerY() {
    document.getElementsByClassName("error-message")[0].textContent = getErrorY();
}

function handleClickX(event) {
    if (event.status !== "success") return;
    let p_input_x = document.getElementsByClassName("p_input_x")[0];
    for (let btn of p_input_x.children) {
        btn.classList.remove("active");
    }
    event.source.classList.add("active");
}
function handleClickR(event) {
    if (event.status !== "success") return;
    let p_input_r = document.getElementsByClassName("p_input_r")[0];
    for (let btn of p_input_r.children) {
        btn.classList.remove("active");
    }
    event.source.classList.add("active");
}
function validateY() {
    return getErrorY() === "";
}
function handleSubmit(event) {
    let status = event.status;
    if (status === 'success') {
        updateCanvas();
    }
    else if (status === 'begin') {
        handlerY();
        if (!validateY()) return true;
    }
}

function handleClear(event) {
    let status = event.status;
    if (status === 'success') {
        updateCanvas();
    }
}
function getErrorY() {

    let input_y = document.getElementsByClassName("input_y")[0];
    let y = input_y.value;

    if (y === "") {
        return "Y не должен быть пустым";
    }
    if (isNaN(y)) {
        return "Y должен быть числом";
    }
    if (String(y).length > 7) {
        return "Y должен быть числом, состоящим из менее 8 символов (<= 7)";
    }
    if (-5 > y || y > 3) {
        return "Y на промежутке [-5;3]";
    }
    return "";
}
let centerX = 225;
let centerY = 225;
let R = 200;
let DEFAULT_R_ = 2;
let canvas = document.getElementById("canvas");
let context = canvas.getContext("2d");
context.font = "16px Verdana";
context.font = "16px Verdana";

function drawPoint(x, y, delta = 2) {
    context.rect(x - delta / 2, y - delta / 2, delta, delta);
}

function get_r_() {
    let input_r = document.getElementsByClassName("p_input_r")[0];

    if (isNaN(+input_r.value) || +input_r.value < 2 || +input_r.value > 5) {
        return DEFAULT_R_;
    }
    return +input_r.value;
}

function drawPointForJSF(mathX, mathY, color = "red", delta = 4) {
    let r_ = get_r_();

    let x = mathX * R / r_ + centerX;
    let y = centerY - mathY * R / r_;

    context.beginPath();
    drawPoint(x, y, delta);
    context.strokeStyle = color;
    context.fillStyle = color;
    context.fill();
    context.stroke();
}

function drawTextWithDeltaX(text, x, y, delta = 4) {
    context.fillText(text, x + delta, y);
    drawPoint(x, y);
}

function drawTextWithDeltaY(text, x, y, delta = 4) {
    context.fillText(text, x, y - delta);
    drawPoint(x, y);
}

function drawArrow(x, y, arrowDelta, direction) {
    context.moveTo(x, y);
    if (direction === "right")
        context.lineTo(x - arrowDelta, y - arrowDelta);
    else
        context.lineTo(x - arrowDelta, y + arrowDelta);

    context.moveTo(x, y);
    if (direction === "right")
        context.lineTo(x - arrowDelta, y + arrowDelta);
    else
        context.lineTo(x + arrowDelta, y + arrowDelta);
}

function drawAxes(radius, delta) {
    let arrowDelta = 4;
    context.beginPath();

    drawPoint(centerX, centerY, 4);

    context.moveTo(centerX - radius - delta, centerY);
    context.lineTo(centerX + radius + delta, centerY);

    drawArrow(centerX + radius + delta, centerY, arrowDelta, "right");
    context.fillText("X", centerX + radius + delta, centerY);

    context.moveTo(centerX, centerY + radius + delta);
    context.lineTo(centerX, centerY - radius - delta);
    drawArrow(centerX, centerY - radius - delta, arrowDelta, "up");
    context.fillText("Y", centerX, centerY - radius - delta);

    drawTextWithDeltaY("-R", centerX - radius, centerY);
    drawTextWithDeltaY("-R/2", centerX - radius / 2, centerY);
    drawTextWithDeltaY("R/2", centerX + radius / 2, centerY);
    drawTextWithDeltaY("R", centerX + radius, centerY);

    drawTextWithDeltaX("-R", centerX, centerY + radius);
    drawTextWithDeltaX("-R/2", centerX, centerY + radius / 2);
    drawTextWithDeltaX("R/2", centerX, centerY - radius / 2);
    drawTextWithDeltaX("R", centerX, centerY - radius);

    context.stroke();
}
function drawFirstQuarter(height, width) {

    context.moveTo(centerX, centerY);

    context.lineTo(centerX + width, centerY);
    context.lineTo(centerX, centerY - height);
    context.lineTo(centerX, centerY);
}

function drawSecondQuarter(height, width) {

    context.moveTo(centerX, centerY);

    context.lineTo(centerX + width, centerY);
    context.lineTo(centerX + width, centerY - height);
    context.lineTo(centerX, centerY - height);
    context.lineTo(centerX, centerY);
}
function drawThirdQuarter(radius) {

    context.moveTo(centerX, centerY);

    context.arc(centerX, centerY, radius,
        Math.PI, Math.PI / 2,
        true);
    context.fill();
}
function drawPlot() {
    context.beginPath();
    drawFirstQuarter(R/2, R);
    drawSecondQuarter(R, -R / 2);
    drawThirdQuarter(R);

    context.closePath();
    context.strokeStyle = "white";
    if(document.getElementsByTagName('link')[3].getAttribute('href') === 'assets/css/injury_blue.css'){
        context.fillStyle = "blue";
    } else {
        context.fillStyle = "black";
    }
    context.fill();
    context.stroke();
    context.strokeStyle = "white";
    context.fillStyle = "black";
    drawAxes(R, 14);
}

function updateCanvas() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    drawPlot();

    for (let tr of document.querySelectorAll("#dataTable tr")) {
        let children = tr.children;

        if (children[0].tagName.toLowerCase() === "td" && children[0].innerText !== "" && children[0].innerText !== "") {
            let x = children[0].innerText;
            let y = children[1].innerText;
            drawPointForJSF(x, y,
                (children[3].innerText === "true" ? "white" : "red")
            );
        }
    }
}

updateCanvas();

canvas.onclick = (event) => {

    let x = event.pageX - event.target.offsetLeft;
    let y = event.pageY - event.target.offsetTop;

    let mathX = x - centerX;
    let mathY = centerY - y;

    let r_ = get_r_();

    let x_ = mathX / R * r_;
    let y_ = mathY / R * r_;


    updateCanvas(
        [
            {name: "x", value: x_.toString()},
            {name: "y", value: y_.toString()},
            {name: "r", value: r_.toString()},
        ]
    )

}
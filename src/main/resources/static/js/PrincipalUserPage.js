(() => {
    let hourElement = document.getElementById('setHour');
    let minElement = document.getElementById('setMin');

    const time = document.getElementById('time');
    const startButton = document.getElementById('start');
    const stopButton = document.getElementById('stop');
    const resetButton = document.getElementById('reset');
    const subButton = document.getElementById('sub');

    //ストップウォッチを開始した時の変数
    let startTime;
    //ストップウォッチを停止した時の変数
    let stopTime = 0;
    //時間のカウントを止める際に必要な変数
    let timeoutID;

    stopButton.disabled = true;
    resetButton.disabled = true;
    subButton.disabled = true;

    function displayTime() {
        //currentTimeは現在時刻からストップウォッチ開始時間を引き、さらに停止時間を足した値
        const currentTime = new Date(Date.now() - startTime + stopTime);
        //padStartは文字列を指定した長さに伸ばすメソッド
        //下のは2つの長さにして、短いものは0を追加する
        const hour = String(currentTime.getHours() - 9).padStart(2, '0');
        const min = String(currentTime.getMinutes()).padStart(2, '0');
        const sec = String(currentTime.getSeconds()).padStart(2, '0');

        time.textContent = `${hour}:${min}:${sec}`;
        timeoutID = setTimeout(displayTime, 10);
    }

    startButton.addEventListener('click', ()=>{
        //ボタンを押したときの現在時刻を取得
        startButton.disabled = true;
        stopButton.disabled = false;
        resetButton.disabled = true;
        subButton.disabled = true;
        //スタートを押した時の時刻を取得
        startTime = Date.now();
        console.log(startTime);
        displayTime();
        });

    stopButton.addEventListener('click', function() {
        startButton.disabled = false;
        stopButton.disabled = true;
        resetButton.disabled = false;
        subButton.disabled = false;

        clearTimeout(timeoutID);
        //ストップボタンを押した時間からスタートボタンを押した時間を引く
        stopTime += (Date.now() - startTime);
    });

    resetButton.addEventListener('click', function() {
        startButton.disabled = false;
        stopButton.disabled = false;
        resetButton.disabled = true;
        subButton.disabled = true;

        //時間を00:00:00にする
        time.textContent = "00:00:00";
        stopTime = 0;
    });

    subButton.addEventListener('click', function() {
        startButton.disabled = false;
        stopButton.disabled = true;
        resetButton.disabled = false;
        subButton.disabled = false;

        let subTime = time.textContent;


        let subHour = subTime.substring(0, 2);
        if (subTime.substring(0, 1) == 0) {
            subHour = subTime.substring(1, 2);
        }

        let subMin = subTime.substring(3, 5);
        if (subTime.substring(3, 4) == 0) {
            subMin = subTime.substring(4, 5);
        }

        hourElement.value = subHour;
        minElement.value = subMin;
    });
})();
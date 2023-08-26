// var data = {
//     // A labels array that can contain any sort of values
//     labels: [1, 2, 3, 4, 5, 6, 7, 8, 9],
//     // Our series array that contains series objects or in this case series data arrays
//     series: [
//       [5, 2, 4, 2, 0, 1]
//     ]
//   };

let obj = JSON.parse(data);

let bobrs = {
    labels: [],
    series: [[]]
};
let amounts = {
    labels: [],
    series: [[]]
};
let incomes = {
    labels: [],
    series: [[]]
};
let processings = {
    labels: [],
    series: [[]]
};

let i = 0;

obj.forEach(e => {
    
    let unix_timestamp = e.metricsDate;
    let date = new Date(unix_timestamp * 1000);
    // let days = date.getDay();
    let hours = date.getHours();
    let minutes = "0" + date.getMinutes();

    let formattedTime = hours + ':' + minutes.slice(-2);

    

    if(i % Math.floor(obj.length / 10) != 0){
        formattedTime = "";
    }

    bobrs.labels.push(formattedTime);
    amounts.labels.push(formattedTime);
    incomes.labels.push(formattedTime);
    processings.labels.push(formattedTime);


    bobrs.series[0].push(e.numBobrs);
    amounts.series[0].push(e.amount);
    incomes.series[0].push(e.incomes);
    processings.series[0].push(e.processingTime);

    i++;
});

console.log(bobrs);

new Chartist.Line('#bobrsChart', bobrs);
new Chartist.Line('#amountChart', amounts);
new Chartist.Line('#incomeChart', incomes);
new Chartist.Line('#processingChart', processings);
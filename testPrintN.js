function printN(n) {
	var i = 0;
	for(i=1; i<=n; i++){
		console.log(i);
	}
}

function printN2(n) {
	if(n){
		printN2(n-1)
		console.log(n)
	}
	return;
}

// 测试 n = 100,  1000, 10000, 100000, ...
printN2(100000)
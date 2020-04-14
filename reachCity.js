
/*
https://leetcode-cn.com/problems/two-city-scheduling/
实现思路：
1. 先都走花费小的，计算总花销为cost（可能去A市 和 B市的人不一样多了）
2. 因为去A市 和 B市的人要一样多，所以 先找出需要改去人少城市的多余花费
3. 把改去另外一个城市的多余花费 按从小到大排序， (这样就找到了多的人中，哪些改去目的地的花费更小)
4. 遍历上面计算的多余花销的数组，把上面计算的多余花销 加上之前的花销cost， 同时修改去A市和B市的人数
*/
function reachCity(costs){
	
	var countA = 0;
	var countB = 0;
	var cost = 0;

	// 1.先都选最近的城市
	costs.forEach(function (tmp) {
		if(tmp[0] > tmp[1]){
			countB++;
			cost += tmp[1];
		}else{
			cost += tmp[0];
			countA++;
		}
	})

	if(countA == countB){
		return;
	}

	// 2.存放countA 和 countB 多的人中，改去另外城市的多余花费
	var disArr = [];
	costs.forEach(function (tmp) {
		// 去A城市的多
		if(countA > countB){
			if(tmp[0] < tmp[1]){// 这个是去A市的
				disArr.push(tmp[1] - tmp[0]);// 改去B市，需要多花的钱
			}
		}else{// 去B城市的多
			if(tmp[0] > tmp[1]){// 这个是去B市的
				disArr.push(tmp[0] - tmp[1]);// 改去A市，需要多花的钱
			}
		}
	})

	// 3.从小到大排序, 把去A市多的人中，改去B市的人花费按照 少到多的排序
	disArr.sort(function (a, b) {
		return a - b;
	});

	// 4.再加上多余花费，同时修改去各个城市的人数
	for(var i = 0; i<disArr.length; i++){
		var distance = disArr[i];
		// 加上改去B市的人的 多余花费
		cost += distance;

		if(countA > countB){// 去A市的多
			countA--;
			countB++;	
		}else{// 去B市的多
			countA++;
			countB--;
		}
		
		// 人数一样了，就结束了
		if(countA == countB){
			break;
		}
	}
	
	return cost;
}


var costs = [[10, 20], [90, 60], [30, 200], [400, 50], [30, 20], [80, 40]];
var result = reachCity(costs);
console.log(result);

// 更优的算法
/*
先按照 去A市区 和 去B市的花费 差值 排序，差值越小，就越适合去A市，差值越大就去B市
找出前n个差值小的，就A市，剩下去B市
 */ 
function reachCityOptimize(costs){
	var result = 0;
	costs = costs.sort(function(a, b){
		return (a[0] - a[1]) - (b[0] - b[1]);
	});

	console.log(costs);

	var n = costs.length / 2;
	for(var i = 0; i < n; i++){
		result += costs[i][0];
	}

	return result;
}

var result = reachCityOptimize(costs);
console.log(result);
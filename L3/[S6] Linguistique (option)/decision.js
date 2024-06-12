var bot;
var bots;
var state;
var transhistory = [];

function printdial(txt, who){
	$("#dialog").append('<div class="dial'+who+'">'+who+': '+txt+'</div>');
}

function initbot(){
	$("#dialog").empty();
	bot = bots[$('#selbot').val()];
	changestate('start');
	transhistory = [];
}

function choosekey(key){
	newstate = bot['etats'][state][1][key];
	transhistory.push(state+'-'+newstate);
	changestate(newstate);
}

function changestate(nextstate){
	if(nextstate in bot['etats']){
		state = nextstate;
		nextstates = [];
		for(key in bot['etats'][state][1]){
			nextstates.push(key);
		}
		if(nextstates.length)
			nextstates = nextstates.join(', ');
		else if('resume' in bot){
			for(transnb in transhistory){
				trans = transhistory[transnb];
				if(trans in bot['resume'])
					printdial(bot['resume'][trans], 'bot');
			}
			nextstates = 'interaction terminée';
		}
		printdial(bot['etats'][state][0]+' ('+nextstates+')', 'bot');
	}else{
		printdial('Oups, cet état n\'existe pas...', 'bot');
	}
}

// Compute the edit distance between the two given strings
function levenshtein(a, b) {
	if (a.length === 0) return b.length;
	if (b.length === 0) return a.length;
	var matrix = [];
	var i;
	for (i = 0; i <= b.length; i++) matrix[i] = [i];
	var j;
	for (j = 0; j <= a.length; j++) matrix[0][j] = j;
	// Fill in the rest of the matrix
	for (i = 1; i <= b.length; i++) {
		for (j = 1; j <= a.length; j++) {
			if (b.charAt(i-1) == a.charAt(j-1))
				matrix[i][j] = matrix[i-1][j-1];
			else
				matrix[i][j] = Math.min(matrix[i-1][j-1] + 1, Math.min(matrix[i][j-1] + 1, matrix[i-1][j] + 1));
		}
	}
	return matrix[b.length][a.length];
}

$(document).ready(function() {
	$("#selbot").change(function(){initbot();});
	$('#input').keyup(function(e){
		var code = e.keyCode ? e.keyCode : e.which;
		if (code == 13) {
			input = $("#input").val().trim();
			$("#input").val('');
			if(input == 'restart'){
				initbot();
				return;
			}
			printdial(input, 'user');
			if(input in bot['etats'][state][1]){
				choosekey(input);
				return;
			}
			bkey = null;
			bdist = 0;
			for(key in bot['etats'][state][1]){
				dist = levenshtein(input, key);
				if(dist/(input.length+key.length) < 0.5 && (bdist == 0 || dist < bdist)){
					bdist = dist;
					bkey = key;
				}
			}
			if(bkey){
				printdial('Votre réponse semble être: '+bkey, 'bot');
				choosekey(bkey);
				return;			
			}
			printdial('Je n\'ai pas compris votre réponse', 'bot');
			changestate(state);
		}
	});
	$.getJSON('decisions.json', function(jsonobject){
		bots = jsonobject;
		for(botid in bots){
			$('#selbot').append($('<option>', {value: botid, text: bots[botid]["description"]}));
		}
		initbot();
	});
});

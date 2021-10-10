class Solution {
public:
    string addString(string s1, string s2) {
	if ( s1.size() == 0)
		return s2;
	if (s2.size() == 0)
		return s1;
	if (s1.size() < s2.size())
		swap(s1, s2);
	int n = s1.size() - 1;
	int m = s2.size() - 1;
	int c = 0;
	string sum = "";
	int sumint = 0;
	while ( n >= 0 || m >= 0 || c > 0) {
		sumint = 0;
		if ( n >= 0) {
			sumint += s1[n] - '0';
			n--;

		}
		if (m >= 0) {
			sumint += s2[m] - '0';
			m--;

		}
		sumint += c;

		c = sumint / 10;
		sum += sumint % 10 + '0';

	}

	reverse(sum.begin(), sum.end());
	return sum;


}

string multiply(string num1, string num2) {
	if ( num1.size() < num2.size()) {
		swap(num1, num2);
	}
    bool isAllzeroes = true;

	int n = num1.size() - 1;
	int m = num2.size() - 1;

	string place = "";
	string prod = "";

	while (m >= 0) {
		char b = num2[m];
		m--;
		int c = 0;
		string line = "";
		while (n >= 0 || c > 0) {

			char a = '0';
			if (n >= 0) {
				a = num1[n] ;

				n--;
			}
			int x = a - '0';
			int y = b - '0';
			// cout << x << y << endl;
			int sum = (c + x * y);
			// cout << "Sum is " << sum << endl;
            if(to_string(sum%10) != "0")
                isAllzeroes = false;
			line += to_string(sum % 10);
			c = sum / 10;
		}


		reverse(line.begin(), line.end());
		line += place;
		place += "0";
		// cout << line << endl;
		prod = addString(prod, line);
		// cout << "prod is " << prod << endl;

		n = num1.size() - 1;



	}
    if(isAllzeroes)
        return "0";
   
	return prod;


}
};

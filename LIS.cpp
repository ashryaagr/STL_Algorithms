class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        vector<int> s;
        for(int val: nums){
            auto it = lower_bound(s.begin(), s.end(), val);
            if(it == s.end()){
                s.push_back(val);
            }
            else{
                if(val < *it){
                *it = val;
                }
            }
            
        }
        return s.size();
    }
};
//LIS in nlogn

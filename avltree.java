import static java.lang.Math.max;

public class BinTree {
    public static void main(String[] args) {
        Dnode bst = new Dnode(5);
        bst.insert(6);
        bst.insert(4);
        bst.insert(1);
        bst.insert(7);
        bst.delete(1);
        bst.delete(5);
    }
}
class Dnode {
    int data,height;
    Dnode right;
    Dnode left;
    Dnode(int dt){
        data = dt;
        right = null;
        left = null;
    }
    int getBalFact(){
        if(this.left == null && this.right == null)
            return 0;
        if(this.left == null)
            return -this.right.height - 1;
        else if(this.right == null)
            return this.left.height + 1;
        else
            return this.left.height - this.right.height;
    }
    Dnode rotateright(){
        Dnode temp = this.left;
        this.left = temp.right;
        temp.right = this;
        if(this.left == null && this.right == null)
            this.height = 1;
        else if(this.left == null)
            this.height = 1 + this.right.height;
        else if(this.right == null)
            this.height = 1 + this.left.height;
        else
            this.height = 1 + max(this.left.height,this.right.height);
        temp.height = max(temp.left.height,temp.right.height) + 1;
        return temp;
    }
    Dnode rotateleft(){
        Dnode temp = this.right;
        this.right = temp.left;
        temp.left = this;
        if(this.left == null && this.right == null)
            this.height = 1;
        else if(this.left == null)
            this.height = 1 + this.right.height;
        else if(this.right == null)
            this.height = 1 + this.left.height;
        else
            this.height = 1 + max(this.left.height,this.right.height);
        temp.height = max(temp.left.height,temp.right.height) + 1;
        return temp;
    }
    Dnode insert(int dt){
        if(dt<this.data) {
            if (this.left != null)
                this.left = this.left.insert(dt);
            else
                this.left = new Dnode(dt);
        }
        else if(dt>this.data){
            if (this.right != null)
                this.right = this.right.insert(dt);
            else
                this.right = new Dnode(dt);
        }
        else{
            return this;
        }
        if(this.left == null && this.right == null)
            this.height = 1;
        else if(this.left == null)
            this.height = 1 + this.right.height;
        else if(this.right == null)
            this.height = 1 + this.left.height;
        else
            this.height = 1 + max(this.left.height,this.right.height);
        int balfact = this.getBalFact();
        if(balfact > 1 && dt < this.left.data)
            return this.rotateright();
        if(balfact < -1 && dt > this.right.data)
            return this.rotateleft();
        if(balfact > 1 && dt > this.left.data){
            this.left = this.left.rotateleft();
            return this.rotateright();
        }
        if(balfact < -1 && dt < this.right.data){
            this.right = this.right.rotateright();
            return this.rotateleft();
        }
        return this;
    }
    void preorder(){
        System.out.println(this.data+" ");
        if(this.left!=null)
            this.left.preorder();
        if(this.right!=null)
            this.right.preorder();
    }
    void inorder(){
        if(this.left!=null)
            this.left.inorder();
        System.out.println(this.data+" ");
        if(this.right!=null)
            this.right.inorder();
    }
    void postorder() {
        if (this.left != null)
            this.left.postorder();
        if (this.right != null)
            this.right.postorder();
        System.out.print(this.data+" ");
    }
    boolean BSTsearchbool(int target){
        Dnode temp = this;
        if(temp.data==target)
            return true;
        else if(temp.data>target){
            if(temp.left!=null)
                return temp.left.BSTsearchbool(target);
            else
                return false;
        }
        else {
            if (temp.right != null)
                return temp.right.BSTsearchbool(target);
            else
                return false;
        }
    }
    Dnode BSTsearchref(int target) {
        Dnode temp = this;
        if (temp.data == target)
            return temp;
        else if (temp.data > target) {
            if (temp.left != null)
                return temp.left.BSTsearchref(target);
            else
                return null;
        } else {
            if (temp.right != null)
                return temp.right.BSTsearchref(target);
            else
                return null;
        }
    }
    int smallestNum(){
        if(this.left!=null)
            return this.left.smallestNum();
        else
            return this.data;
    }
    Dnode smallestNode(){
        if(this.left!=null)
            return this.left.smallestNode();
        else
            return this;
    }
    boolean BSTcheck(int min, int max){
        if(this.left==null && this.right!=null)
            return (this.data>min && this.data<max && this.right.BSTcheck(this.data,max));
        else if(this.left!=null && this.right==null)
            return (this.data>min && this.data<max && this.left.BSTcheck(min,this.data));
        else if(this.left!=null && this.right!=null)
            return (this.data>min && this.data<max && this.right.BSTcheck(this.data,max) && this.left.BSTcheck(min,this.data));
        else
            return (this.data>min && this.data<max);
    }
    Dnode delete(int value){
        Dnode temp = this;
        if(temp.data>value){
            if(temp.left!=null)
                temp.left = temp.left.delete(value);
            else
                temp.left = null;
        }
        else if(temp.data<value) {
            if (temp.right != null)
                temp.right = temp.right.delete(value);
            else
                temp.right = null;
        }
        else {
            if (temp.left != null && temp.right != null) {
                Dnode temp2 = temp;
                Dnode minRight = temp2.right.smallestNode();
                temp.data = minRight.data;
                temp.right = temp.right.delete(minRight.data);
            } else if (temp.left != null)
                temp = temp.left;
            else if (temp.right != null)
                temp = temp.right;
            else
                temp = null;
        }
        if(temp == null) {
            return temp;
        }
        if(temp.left == null && temp.right == null)
            temp.height = 1;
        else if(temp.left == null)
            temp.height = temp.right.height + 1;
        else if(temp.right == null)
            temp.height = temp.left.height + 1;
        else
            temp.height = max(temp.left.height, temp.right.height) +1;
        int balfact = temp.getBalFact();
        if(balfact > 1 && temp.left.getBalFact() >= 0)
            return temp.rotateright();
        if(balfact < -1 && temp.right.getBalFact() <= 0)
            return temp.rotateleft();
        if(balfact > 1 && temp.left.getBalFact() < 0){
            temp.left = temp.left.rotateleft();
            return temp.rotateright();
        }
        if(balfact < -1 && temp.right.getBalFact() > 0){
            temp.right = temp.right.rotateright();
            return temp.rotateleft();
        }
        return temp;
    }
}

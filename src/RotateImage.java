public class RotateImage {

    public static void main(String[] args) {

        int[][] image = {
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
        };
        printImage(image);
        rotateImage90Degree(image);
        printImage(image);
    }
    /*
    [00][01][02][03][04]  임시=00 00=04 04=44 44=40 40=임시
    [10][11][12][13][14]  임시=11 11=13 13=33 33=31 31=임시
    [20][21][22][23][24]  s = 0 , e = 4 첫레이어는 0~4까지
    [30][31][32][33][34]  s = 1 , e = 3 두번째 레이어는 1~3까지
    [40][41][42][43][44]  갈수록 s는 하나씩 증가 e는 하나씩 감소 s++ ,e--

    temp = top
    top = right
    right = bottom
    bottom = left
    left = temp
     */
    //90도 기울기
    private static int[][] rotateImage90Degree(int[][] image) {
        int temp;
        for (int s1 = 0, e1 = image.length - 1; s1 < e1; s1++, e1--) {
            for (int s2 = s1, e2 = e1; s2 < e1; s2++, e2--) {
                temp = image[s1][s2]; // 00, 01, 02 ,03
                image[s1][s2] = image[s2][e1]; //top  00 , 01 , 02 , 03 = 04 , 14 , 24 , 34
                image[s2][e1] = image[e1][e2]; //right 04 , 14, 24, 34 = 44 , 43 , 42 , 41
                image[e1][e2] = image[e2][s1]; //bottom 44 , 43 , 42 , 41 = 40 , 30 , 20 , 10
                image[e2][s1] = temp;          //left 40 , 30 , 20 , 10 = 00 ,01 , 02 , 03
            }
        }
        return image;
    }

    private static void printImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                Util.print(image[i][j] + " ");
            }
            Util.println("");
        }
        Util.println("");
    }
}

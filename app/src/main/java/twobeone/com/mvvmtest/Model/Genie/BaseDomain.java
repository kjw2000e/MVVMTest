package twobeone.com.mvvmtest.Model.Genie;

public class BaseDomain {

         /*   "resultCode": "0",
                "itemSize": 1,
                "resultMessage": "성공", */

    private String resultCode;
    private String resultMessage;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}

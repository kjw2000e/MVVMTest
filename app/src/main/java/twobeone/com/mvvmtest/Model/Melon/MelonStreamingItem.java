package twobeone.com.mvvmtest.Model.Melon;

/**
 * Created by jiwon on 2018-01-04.
 */

public class MelonStreamingItem {
    String RESULT;
    String ACTIONCODE = "100";
    String MESSAGE;
    String VIEWTYPE;
    String staticDomain;
    String httpsDomain;

    PATHINFO GETPATHINFO;
    Object CONTENTSINFO;
    boolean ISFLACSTOK;
    boolean ISFLAC16STALLOK;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getACTIONCODE() {
        return ACTIONCODE;
    }

    public void setACTIONCODE(String ACTIONCODE) {
        this.ACTIONCODE = ACTIONCODE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getVIEWTYPE() {
        return VIEWTYPE;
    }

    public void setVIEWTYPE(String VIEWTYPE) {
        this.VIEWTYPE = VIEWTYPE;
    }

    public String getStaticDomain() {
        return staticDomain;
    }

    public void setStaticDomain(String staticDomain) {
        this.staticDomain = staticDomain;
    }

    public String getHttpsDomain() {
        return httpsDomain;
    }

    public void setHttpsDomain(String httpsDomain) {
        this.httpsDomain = httpsDomain;
    }

    public PATHINFO getGETPATHINFO() {
        return GETPATHINFO;
    }

    public void setGETPATHINFO(PATHINFO GETPATHINFO) {
        this.GETPATHINFO = GETPATHINFO;
    }

    public class PATHINFO {
        String PLAYTIME;        //-1 : 전체재생, 나머지는 입력된 숫자만큼만 재생
        String CID;
        String PATH;
        String LOGGINGTOKEN;
        String PROTOCOLTYPE;
        boolean ISHTTPS;
        String C;
        String METATYPE;
        String BITRATE;
        boolean ISSTOWNPRODUCT;
        String FILEUPDTDATE;
        String FILESIZE;

        public String getPLAYTIME() {
            return PLAYTIME;
        }

        public void setPLAYTIME(String PLAYTIME) {
            this.PLAYTIME = PLAYTIME;
        }

        public String getCID() {
            return CID;
        }

        public void setCID(String CID) {
            this.CID = CID;
        }

        public String getPATH() {
            return PATH;
        }

        public void setPATH(String PATH) {
            this.PATH = PATH;
        }

        public String getLOGGINGTOKEN() {
            return LOGGINGTOKEN;
        }

        public void setLOGGINGTOKEN(String LOGGINGTOKEN) {
            this.LOGGINGTOKEN = LOGGINGTOKEN;
        }

        public String getPROTOCOLTYPE() {
            return PROTOCOLTYPE;
        }

        public void setPROTOCOLTYPE(String PROTOCOLTYPE) {
            this.PROTOCOLTYPE = PROTOCOLTYPE;
        }

        public boolean isISHTTPS() {
            return ISHTTPS;
        }

        public void setISHTTPS(boolean ISHTTPS) {
            this.ISHTTPS = ISHTTPS;
        }

        public String getC() {
            return C;
        }

        public void setC(String c) {
            C = c;
        }

        public String getMETATYPE() {
            return METATYPE;
        }

        public void setMETATYPE(String METATYPE) {
            this.METATYPE = METATYPE;
        }

        public String getBITRATE() {
            return BITRATE;
        }

        public void setBITRATE(String BITRATE) {
            this.BITRATE = BITRATE;
        }

        public boolean isISSTOWNPRODUCT() {
            return ISSTOWNPRODUCT;
        }

        public void setISSTOWNPRODUCT(boolean ISSTOWNPRODUCT) {
            this.ISSTOWNPRODUCT = ISSTOWNPRODUCT;
        }

        public String getFILEUPDTDATE() {
            return FILEUPDTDATE;
        }

        public void setFILEUPDTDATE(String FILEUPDTDATE) {
            this.FILEUPDTDATE = FILEUPDTDATE;
        }

        public String getFILESIZE() {
            return FILESIZE;
        }

        public void setFILESIZE(String FILESIZE) {
            this.FILESIZE = FILESIZE;
        }
    }

    public Object getCONTENTSINFO() {
        return CONTENTSINFO;
    }

    public void setCONTENTSINFO(Object CONTENTSINFO) {
        this.CONTENTSINFO = CONTENTSINFO;
    }

    public boolean isISFLACSTOK() {
        return ISFLACSTOK;
    }

    public void setISFLACSTOK(boolean ISFLACSTOK) {
        this.ISFLACSTOK = ISFLACSTOK;
    }

    public boolean isISFLAC16STALLOK() {
        return ISFLAC16STALLOK;
    }

    public void setISFLAC16STALLOK(boolean ISFLAC16STALLOK) {
        this.ISFLAC16STALLOK = ISFLAC16STALLOK;
    }
}


package com.zwh.demo.ui.demo.bean;

import java.util.List;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2016/12/08 10:50
 */

public class Panorama {


    /**
     * Admission : GS(2015)2210
     * Date : 20130817
     * DeviceHeight : 1.45
     * FileTag : pano_cfg
     * Heading : 268.2
     * ID : 0100220000130817164838355J5
     * ImgLayer : [{"BlockX":2,"BlockY":1,"ImgFormat":"jpg","ImgLevel":1},{"BlockX":4,"BlockY":2,"ImgFormat":"jpg","ImgLevel":2},{"BlockX":8,"BlockY":4,"ImgFormat":"jpg","ImgLevel":3},{"BlockX":16,"BlockY":8,"ImgFormat":"jpg","ImgLevel":4}]
     * LayerCount : 4
     * Mode : day
     * MoveDir : 268.201
     * NorthDir : 1.8
     * Obsolete : 1
     * Pitch : -0.983
     * PoiDir : null
     * Provider : 1
     * Rname :
     * Roads : [{"ID":"8bed12-4b95-be8a-4e2c-b17e2e","IsCurrent":1,"Panos":[{"DIR":0,"Order":0,"PID":"0100220000130817164838355J5","Type":"street","X":1295816749,"Y":482579315}]}]
     * Roll : -0.484
     * SwitchID : []
     * Time : 201308
     * TimeLine : [{"ID":"0100220000130817164838355J5","IsCurrent":1,"Time":"day","TimeDir":"","TimeLine":"201308","Year":"2013"},{"ID":"09002200011601131527202482L","IsCurrent":0,"Time":"day","TimeDir":"uptodate","TimeLine":"201601","Year":"2016"}]
     * Type : street
     * UserID :
     * Version : 0
     * Z : 36.68
     * format_v : 0
     * panoinfo : null
     * plane :
     * procdate : 20160902
     * X : 12958167
     * RX : 12958168
     * Y : 4825793
     * RY : 4825779
     */

    private String Admission;
    private String Date;
    private double DeviceHeight;
    private String FileTag;
    private double Heading;
    private String ID;
    private int LayerCount;
    private String Mode;
    private double MoveDir;
    private double NorthDir;
    private int Obsolete;
    private double Pitch;
    private Object PoiDir;
    private int Provider;
    private String Rname;
    private double Roll;
    private String Time;
    private String Type;
    private String UserID;
    private String Version;
    private double Z;
    private String format_v;
    private Object panoinfo;
    private String plane;
    private String procdate;
    private int X;
    private int RX;
    private int Y;
    private int RY;
    /**
     * BlockX : 2
     * BlockY : 1
     * ImgFormat : jpg
     * ImgLevel : 1
     */

    private List<ImgLayerBean> ImgLayer;
    /**
     * ID : 8bed12-4b95-be8a-4e2c-b17e2e
     * IsCurrent : 1
     * Panos : [{"DIR":0,"Order":0,"PID":"0100220000130817164838355J5","Type":"street","X":1295816749,"Y":482579315}]
     */

    private List<RoadsBean> Roads;
    private List<?> SwitchID;
    /**
     * ID : 0100220000130817164838355J5
     * IsCurrent : 1
     * Time : day
     * TimeDir :
     * TimeLine : 201308
     * Year : 2013
     */

    private List<TimeLineBean> TimeLine;

    public String getAdmission() {
        return Admission;
    }

    public void setAdmission(String Admission) {
        this.Admission = Admission;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public double getDeviceHeight() {
        return DeviceHeight;
    }

    public void setDeviceHeight(double DeviceHeight) {
        this.DeviceHeight = DeviceHeight;
    }

    public String getFileTag() {
        return FileTag;
    }

    public void setFileTag(String FileTag) {
        this.FileTag = FileTag;
    }

    public double getHeading() {
        return Heading;
    }

    public void setHeading(double Heading) {
        this.Heading = Heading;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getLayerCount() {
        return LayerCount;
    }

    public void setLayerCount(int LayerCount) {
        this.LayerCount = LayerCount;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public double getMoveDir() {
        return MoveDir;
    }

    public void setMoveDir(double MoveDir) {
        this.MoveDir = MoveDir;
    }

    public double getNorthDir() {
        return NorthDir;
    }

    public void setNorthDir(double NorthDir) {
        this.NorthDir = NorthDir;
    }

    public int getObsolete() {
        return Obsolete;
    }

    public void setObsolete(int Obsolete) {
        this.Obsolete = Obsolete;
    }

    public double getPitch() {
        return Pitch;
    }

    public void setPitch(double Pitch) {
        this.Pitch = Pitch;
    }

    public Object getPoiDir() {
        return PoiDir;
    }

    public void setPoiDir(Object PoiDir) {
        this.PoiDir = PoiDir;
    }

    public int getProvider() {
        return Provider;
    }

    public void setProvider(int Provider) {
        this.Provider = Provider;
    }

    public String getRname() {
        return Rname;
    }

    public void setRname(String Rname) {
        this.Rname = Rname;
    }

    public double getRoll() {
        return Roll;
    }

    public void setRoll(double Roll) {
        this.Roll = Roll;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double Z) {
        this.Z = Z;
    }

    public String getFormat_v() {
        return format_v;
    }

    public void setFormat_v(String format_v) {
        this.format_v = format_v;
    }

    public Object getPanoinfo() {
        return panoinfo;
    }

    public void setPanoinfo(Object panoinfo) {
        this.panoinfo = panoinfo;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getProcdate() {
        return procdate;
    }

    public void setProcdate(String procdate) {
        this.procdate = procdate;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getRX() {
        return RX;
    }

    public void setRX(int RX) {
        this.RX = RX;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getRY() {
        return RY;
    }

    public void setRY(int RY) {
        this.RY = RY;
    }

    public List<ImgLayerBean> getImgLayer() {
        return ImgLayer;
    }

    public void setImgLayer(List<ImgLayerBean> ImgLayer) {
        this.ImgLayer = ImgLayer;
    }

    public List<RoadsBean> getRoads() {
        return Roads;
    }

    public void setRoads(List<RoadsBean> Roads) {
        this.Roads = Roads;
    }

    public List<?> getSwitchID() {
        return SwitchID;
    }

    public void setSwitchID(List<?> SwitchID) {
        this.SwitchID = SwitchID;
    }

    public List<TimeLineBean> getTimeLine() {
        return TimeLine;
    }

    public void setTimeLine(List<TimeLineBean> TimeLine) {
        this.TimeLine = TimeLine;
    }

    public static class ImgLayerBean {
        private int BlockX;
        private int BlockY;
        private String ImgFormat;
        private int ImgLevel;

        public int getBlockX() {
            return BlockX;
        }

        public void setBlockX(int BlockX) {
            this.BlockX = BlockX;
        }

        public int getBlockY() {
            return BlockY;
        }

        public void setBlockY(int BlockY) {
            this.BlockY = BlockY;
        }

        public String getImgFormat() {
            return ImgFormat;
        }

        public void setImgFormat(String ImgFormat) {
            this.ImgFormat = ImgFormat;
        }

        public int getImgLevel() {
            return ImgLevel;
        }

        public void setImgLevel(int ImgLevel) {
            this.ImgLevel = ImgLevel;
        }
    }

    public static class RoadsBean {
        private String ID;
        private int IsCurrent;
        /**
         * DIR : 0
         * Order : 0
         * PID : 0100220000130817164838355J5
         * Type : street
         * X : 1295816749
         * Y : 482579315
         */

        private List<PanosBean> Panos;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public int getIsCurrent() {
            return IsCurrent;
        }

        public void setIsCurrent(int IsCurrent) {
            this.IsCurrent = IsCurrent;
        }

        public List<PanosBean> getPanos() {
            return Panos;
        }

        public void setPanos(List<PanosBean> Panos) {
            this.Panos = Panos;
        }

        public static class PanosBean {
            private int DIR;
            private int Order;
            private String PID;
            private String Type;
            private int X;
            private int Y;

            public int getDIR() {
                return DIR;
            }

            public void setDIR(int DIR) {
                this.DIR = DIR;
            }

            public int getOrder() {
                return Order;
            }

            public void setOrder(int Order) {
                this.Order = Order;
            }

            public String getPID() {
                return PID;
            }

            public void setPID(String PID) {
                this.PID = PID;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public int getX() {
                return X;
            }

            public void setX(int X) {
                this.X = X;
            }

            public int getY() {
                return Y;
            }

            public void setY(int Y) {
                this.Y = Y;
            }
        }
    }

    public static class TimeLineBean {
        private String ID;
        private int IsCurrent;
        private String Time;
        private String TimeDir;
        private String TimeLine;
        private String Year;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public int getIsCurrent() {
            return IsCurrent;
        }

        public void setIsCurrent(int IsCurrent) {
            this.IsCurrent = IsCurrent;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getTimeDir() {
            return TimeDir;
        }

        public void setTimeDir(String TimeDir) {
            this.TimeDir = TimeDir;
        }

        public String getTimeLine() {
            return TimeLine;
        }

        public void setTimeLine(String TimeLine) {
            this.TimeLine = TimeLine;
        }

        public String getYear() {
            return Year;
        }

        public void setYear(String Year) {
            this.Year = Year;
        }
    }
}

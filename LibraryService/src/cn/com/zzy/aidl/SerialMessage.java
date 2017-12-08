package cn.com.zzy.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BCLZzy on 2017/12/4.
 * AIDL 自定义数据类型 : 售货机消息
 *
 * Parcel : 数据打包，进程间传递的数据包。
 */

public class SerialMessage implements Parcelable {

    private byte command;//命令字
    private byte[] data;//data
    private byte result;//返回结果
    private boolean isNeedResult;//是否需要返回结果
    private int flag;//标识

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public byte getCommand() {
        return command;
    }

    public void setCommand(byte command) {
        this.command = command;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public boolean isNeedResult() {
        return isNeedResult;
    }

    public void setNeedResult(boolean needResult) {
        isNeedResult = needResult;
    }
    //AS插件自动生成部分----------------------------------------------------------------------------------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.command);
        dest.writeByteArray(this.data);
        dest.writeByte(this.result);
        dest.writeByte(this.isNeedResult ? (byte) 1 : (byte) 0);
        dest.writeInt(this.flag);
    }

    public SerialMessage() {
    }

    protected SerialMessage(Parcel in) {
        this.command = in.readByte();
        this.data = in.createByteArray();
        this.result = in.readByte();
        this.isNeedResult = in.readByte() != 0;
        this.flag = in.readInt();
    }

    public static final Creator<SerialMessage> CREATOR = new Creator<SerialMessage>() {
        @Override
        public SerialMessage createFromParcel(Parcel source) {
            return new SerialMessage(source);
        }

        @Override
        public SerialMessage[] newArray(int size) {
            return new SerialMessage[size];
        }
    };
}

package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.Item;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.enums.EmployeeClassification;

public class TransactionTransition implements Parcelable {
    private List<Item> selectedItems;
    private String employeeId;
    private String totalPrice;
    private String paymentMethod;
    private String saleId;

    public TransactionTransition(Parcel transactionTransitionParcel) {
        //this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();
        this.saleId = transactionTransitionParcel.readString();
        //this.totalPrice = employeeTransitionParcel.readString();
        //this.paymentMethod = employeeTransitionParcel.readString();
        //this.employeeId = employeeTransitionParcel.readString();
        //this.selectedItems = employeeTransitionParcel.readString();
        //this.active = (employeeTransitionParcel.readInt() != 0);
        //this.classification = EmployeeClassification.mapValue(employeeTransitionParcel.readInt());
        //this.managerId = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();

        //this.createdOn = new Date();
        //this.createdOn.setTime(employeeTransitionParcel.readLong());
    }

    public TransactionTransition(Transaction t) {
        //this.saleId = t.saleId;
    }


    @Override
    public void writeToParcel(Parcel destination, int flags) {
        /*destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.employeeId);
        destination.writeString(this.firstName);
        destination.writeString(this.lastName);
        destination.writeString(this.password);
        destination.writeInt(this.active ? 1 : 0);
        destination.writeInt(this.classification.getValue());
        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.managerId).execute());
        destination.writeLong(this.createdOn.getTime());*/
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<EmployeeTransition> CREATOR = new Parcelable.Creator<EmployeeTransition>() {
        public EmployeeTransition createFromParcel(Parcel employeeTransitionParcel) {
            return new EmployeeTransition(employeeTransitionParcel);
        }

        public EmployeeTransition[] newArray(int size) {
            return new EmployeeTransition[size];
        }
    };
}

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
    //private List<Item> selectedItems;
    private int employeeId;
    private int total;
    private String paymentMethod;
    private int saleId;

    public int getTotal() {
        return this.total;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public int getSaleId() {
        return saleId;
    }

    public TransactionTransition() {
        this.employeeId = 0;
        this.total = 0;
        this.paymentMethod = "cash";
        this.saleId = 0;
    }

    public TransactionTransition(Parcel transactionTransitionParcel) {
        //this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();
        this.saleId = transactionTransitionParcel.readInt();
        this.total = transactionTransitionParcel.readInt();
        this.paymentMethod = transactionTransitionParcel.readString();
        this.employeeId = transactionTransitionParcel.readInt();
        //this.totalPrice = employeeTransitionParcel.readString();
        //this.paymentMethod = employeeTransitionParcel.readString();
        //this.employeeId = employeeTransitionParcel.readString();
        //this.selectedItems = employeeTransitionParcel.readString();
        //this.active = (employeeTransitionParcel.readInt() != 0);
        //this.classification = EmployeeClassification.mapValue(employeeTransitionParcel.readInt());
        //this.managerId = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();

        //this.createdOn = new Date();
        //this.createdOn.setTime(employeeTransitionParcel.readLong());

        this.saleId = transactionTransitionParcel.readInt();
        this.total = transactionTransitionParcel.readInt();
        this.paymentMethod = transactionTransitionParcel.readString();
        this.employeeId = transactionTransitionParcel.readInt();
    }

    public TransactionTransition(Transaction t) {
        this.saleId = t.saleId;
        this.total = t.total;
        this.paymentMethod = t.paymentMethod;
        this.employeeId = Integer.parseInt(t.employeeId);
    }


    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeInt(this.saleId);
        destination.writeInt(this.total);
        destination.writeString(this.paymentMethod);
        destination.writeInt(this.employeeId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<TransactionTransition> CREATOR = new Parcelable.Creator<TransactionTransition>() {
        public TransactionTransition createFromParcel(Parcel transactionTransitionParcel) {
            return new TransactionTransition(transactionTransitionParcel);
        }

        public TransactionTransition[] newArray(int size) {
            return new TransactionTransition[size];
        }
    };
}

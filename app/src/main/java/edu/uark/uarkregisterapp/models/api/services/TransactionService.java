package edu.uark.uarkregisterapp.models.api.services;

import org.json.JSONObject;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.enums.ApiObject;
import edu.uark.uarkregisterapp.models.api.enums.TransactionApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class TransactionService extends BaseRemoteService {

    public TransactionService() { super(ApiObject.TRANSACTION); }

    public ApiResponse<Transaction> createTransaction(Transaction transaction) {
        return this.readTransactionDetailsFromResponse(
                this.<Transaction>performPostRequest(
                        this.buildPath(
                                (new PathElementInterface[] {TransactionApiMethod.CREATE_TRANSACTION})
                        ),
                        transaction.convertToJson()
                )
        );
    }

    private ApiResponse<Transaction> readTransactionDetailsFromResponse(ApiResponse<Transaction> apiResponse) {
        return this.readDetailsFromResponse(
                apiResponse, (new Transaction())
        );
    }

    private <T extends LoadFromJsonInterface<T>> ApiResponse<T> readDetailsFromResponse(ApiResponse<T> apiResponse, T apiObject) {
        JSONObject rawJsonObject = this.rawResponseToJSONObject(
                apiResponse.getRawResponse()
        );

        if (rawJsonObject != null) {
            apiResponse.setData(
                    apiObject.loadFromJson(rawJsonObject)
            );
        }

        return apiResponse;
    }

}

package umc.spring_study.apiPayload.exception.handler;

import umc.spring_study.apiPayload.code.status.ErrorStatus;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
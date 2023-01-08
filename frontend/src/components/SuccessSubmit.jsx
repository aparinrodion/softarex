import React from 'react';

const SuccessSubmit = () => {
    return (
        <div className={"bg-light vh-100"}>
            <div className={"justify-content-center d-flex"}>
                <div
                    className={"d-flex flex-column align-items-center " +
                        "justify-content-center bg-white px-5 py-3 " +
                        "rounded-2 border mt-5 fw-semibold"}>
                    <p>Thank you!</p>
                    <span>Your response was saved.</span>
                </div>
            </div>
        </div>
    );
};

export default SuccessSubmit;
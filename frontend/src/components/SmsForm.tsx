import React, { useState } from "react";
import {
    Button,
    TextField,
    Typography,
    Snackbar,
    Alert,
} from "@mui/material";
import { sendSms } from "../services/smsService";

const SmsForm = () => {
    const [phone, setPhoneNumber] = useState("");
    const [message, setMessage] = useState("");
    const [snackbar, setSnackbar] = useState({
        open: false,
        message: "",
        severity: "success" as "success" | "error",
    });

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await sendSms({ phone, message });
            setSnackbar({
                open: true,
                message: "SMS sent successfully!",
                severity: "success",
            });
            setPhoneNumber("");
            setMessage("");
        } catch (error: any) {
            setSnackbar({
                open: true,
                message: error.message || "Failed to send SMS.",
                severity: "error",
            });
        }
    };

    return (
        <>
            <Typography variant="h5" gutterBottom>
                Send SMS
            </Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    fullWidth
                    margin="normal"
                    label="Phone Number"
                    variant="outlined"
                    value={phone}
                    onChange={(e) => setPhoneNumber(e.target.value)}
                />
                <TextField
                    fullWidth
                    margin="normal"
                    label="Message"
                    multiline
                    rows={4}
                    variant="outlined"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                />
                <Button
                    fullWidth
                    type="submit"
                    variant="contained"
                    sx={{ mt: 2 }}
                    disabled={!phone || !message}
                >
                    Send
                </Button>
            </form>

            <Snackbar
                open={snackbar.open}
                autoHideDuration={4000}
                onClose={() => setSnackbar({ ...snackbar, open: false })}
                anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
            >
                <Alert
                    severity={snackbar.severity}
                    onClose={() => setSnackbar({ ...snackbar, open: false })}
                    sx={{ width: "100%" }}
                >
                    {snackbar.message}
                </Alert>
            </Snackbar>
        </>
    );
};

export default SmsForm;

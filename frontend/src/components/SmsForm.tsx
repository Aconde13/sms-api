import { useState } from 'react';
import { TextField, Button, Snackbar, Alert, Stack } from '@mui/material';
import {sendSms} from "../services/smsService.ts";

export default function SmsForm() {
    const [phone, setPhone] = useState('');
    const [message, setMessage] = useState('');
    const [success, setSuccess] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            await sendSms({ phone, message });
            setSuccess(true);
            setPhone('');
            setMessage('');
        } catch (err: any) {
            setError(err.message || 'Something went wrong');
        }
    };

    return (
        <>
            <form onSubmit={handleSubmit}>
                <Stack spacing={3}>
                    <TextField
                        label="Phone Number"
                        variant="outlined"
                        fullWidth
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                    />
                    <TextField
                        label="Message"
                        multiline
                        rows={4}
                        variant="outlined"
                        fullWidth
                        value={message}
                        onChange={(e) => setMessage(e.target.value)}
                    />
                    <Button variant="contained" color="primary" type="submit">
                        Send
                    </Button>
                </Stack>
            </form>

            <Snackbar open={success} autoHideDuration={3000} onClose={() => setSuccess(false)}>
                <Alert severity="success" onClose={() => setSuccess(false)}>
                    Message sent successfully!
                </Alert>
            </Snackbar>

            <Snackbar open={!!error} autoHideDuration={3000} onClose={() => setError(null)}>
                <Alert severity="error" onClose={() => setError(null)}>
                    {error}
                </Alert>
            </Snackbar>
        </>
    );
}

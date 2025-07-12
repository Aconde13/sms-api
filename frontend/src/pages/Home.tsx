import {Box, Paper } from '@mui/material';
import SmsForm from '../components/SmsForm';

export default function Home() {
    return (
            <Box sx={{
                minHeight: "100vh",
                background: "linear-gradient(to right, #667eea, #764ba2)",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                padding: 2,
            }}
            >
                <Paper elevation={6} sx={{
                    padding: 4,
                    maxWidth: 420,
                    width: "100%",
                    borderRadius: 3,
                }}
                >
                    <SmsForm />
                </Paper>
            </Box>
    );
}

import { Container, Typography, Box, Paper } from '@mui/material';
import SmsForm from '../components/SmsForm';

export default function Home() {
    return (
        <Container maxWidth="sm">
            <Box mt={10}>
                <Paper elevation={3} sx={{ padding: 4 }}>
                    <Typography variant="h4" component="h1" gutterBottom>
                        Send SMS
                    </Typography>
                    <SmsForm />
                </Paper>
            </Box>
        </Container>
    );
}

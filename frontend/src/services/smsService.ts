export interface SmsPayload {
    phone: string;
    message: string;
}

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function sendSms({ phone, message }: SmsPayload): Promise<void> {
    const response = await fetch(`${API_BASE_URL}/api/sms/send`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ phone, message }),
    });

    if (!response.ok) {
        const { error } = await response.json();
        throw new Error(error || 'Unexpected error');
    }
}

from flask import Flask, request, jsonify # type: ignore
import requests # type: ignore
import os

app = Flask(__name__)

OLLAMA_API_URL = "http://localhost:11434/api/generate"
DEFAULT_MODEL = "llama3:8b"

def get_llm_response(message, model=DEFAULT_MODEL):
    try:
        data = {
            "model": model,
            "prompt": message,
            "stream": False
        }
        
        response = requests.post(
            OLLAMA_API_URL,
            json=data,
            headers={"Content-Type": "application/json"}
        )
        
        if response.status_code == 200:
            return response.json().get("response", "Cevap alınamadı")
        else:
            return f"Hata: {response.status_code}"
    except Exception as e:
        return f"Bağlantı hatası: {str(e)}"


@app.route('/chat', methods=['POST'])
def chat():
    data = request.json
    if not data or 'message' not in data:
        return jsonify({"error": "Mesaj girmelisiniz"}), 400
    
    message = data['message']
    model = data.get('model', DEFAULT_MODEL)
    
    try:
        response = get_llm_response(message, model)
        return jsonify({"response": response})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, host='127.0.0.1', port=5000) 
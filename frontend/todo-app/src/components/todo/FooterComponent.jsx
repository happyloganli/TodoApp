import { useContext } from "react";
import { AuthContext } from "./security/BasicAuthContext";

function FooterComponent() {
    const authContext = useContext(AuthContext)

    return (
        <footer className="footer">
            <div className="container">
              ${authContext.username}
            </div>
        </footer>
    )
}

export default FooterComponent
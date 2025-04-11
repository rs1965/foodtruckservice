const validateFields = (fields, mode) => {
    let errors = {};

    fields.forEach(field => {
        const { name, value, required } = field;
        if (mode.toUpperCase() === 'RESET') {
            // Clear all error messages
            errors[name] = "";
        } else if (mode.toUpperCase() === 'ONCHANGE') {
            // Only validate the field that is being changed
            if (required && (value === "" || value === null || value === undefined || (name === 'agreement' && !value))) {
                errors[name] = `${name} is required.`;
            } else {
                errors[name] = "";
            }
        } else if (mode.toUpperCase() === 'ONSAVE') {
            // Validate all fields
            if (required && (value === "" || value === null || value === undefined || (name === 'agreement' && !value))) {
                errors[name] = `${name} is required.`;
            } else {
                errors[name] = "";
            }
        }
    });

    return errors;
};

export default validateFields